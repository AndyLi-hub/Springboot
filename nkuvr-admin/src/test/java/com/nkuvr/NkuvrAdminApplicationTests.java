package com.nkuvr;

import com.nkuvr.pojo.Appointment;
import com.nkuvr.pojo.User;
import com.nkuvr.service.IAppointmentService;
import com.nkuvr.service.IUserService;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class NkuvrAdminApplicationTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAppointmentService appointmentService;

    @Test
    void findAppointmentListByUserIdTest() {
        List<Appointment> list = appointmentService.findAppointmentListByUserId(1L);
        for (Appointment appointment : list) {
            System.out.println(appointment.getUser());
        }
    }

//    @Test
//    void getAllUser() {
//        return userMapper.findAll();users = userService.findAll();
//        System.out.println(users);
//    }

    @Test
    void findUserByStudentNumberTest() {
        User userByStudentNumber = userService.findUserByStudentNumber("17990425");
        if (userByStudentNumber == null) {
            System.out.println("giao!!!!");
        }
    }

    @Test
    void splitTest() {
        String str = "id=2&阿伟大单位";
        String[] reason = str.split("&");
        System.out.println(reason[reason.length-1]);

    }

    @Test
    public void testDeleteUsersByIds() {
        // 定义一个用户ID数组
        Long[] idsToDelete = {1L, 2L};

        // 调用删除方法
        userService.piLiangDelete(idsToDelete);

        // 这里可以添加断言来验证是否删除成功，例如查询已删除的用户是否存在

    }

//    @Test
//    void addBatch() {
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 300000; i++) {
//            User user = new User();
//            user.setStudentNumber("1" + i); // 确保有唯一值
//            user.setPassword("password" + i);
//            user.setGender(i % 2); // 交替性别
//            user.setRemark("remark" + i);
//            user.setState(1);
//            user.setRealName("User" + i);
//            user.setCreateTime(new Date());
//            user.setUpdateTime(new Date());
//            users.add(user);
//        }
//        userService.userAdd(users);
//    }

//    @Test
//    void deleteBatchTest() {
//        userService.deleteUserById();
//    }


}
