package com.nkuvr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkuvr.utils.ResultUtil;
import com.nkuvr.pojo.User;
import com.nkuvr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: weizujie
 * @Date: 2020/4/25
 * @Version: 1.0
 * @Github: https://github.com/weizujie
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 跳转到后台首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String toUserIndex() {
        return "user/userIndex";
    }

    /**
     * 跳转到用户列表页面并展用户数据
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String toUserList(@RequestParam(name = "page", defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 10);
        List<User> userList = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "user/userList";
    }

    /**
     * 跳转用户编辑界面并展示用户数据
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String toUserEdit(@PathVariable Long id, Model model) {
        User dbUser = userService.findUserById(id);
        model.addAttribute("userInfo", dbUser);
        return "user/userEdit";
    }

    /**
     * 处理用户编辑
     *
     * @param user
     * @return
     */
    @RequestMapping("/doEdit")
    @ResponseBody
    public ResultUtil doUserEdit(User user) {
        ResultUtil result = new ResultUtil();
        try {
            userService.userEdit(user);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 处理用户删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultUtil doUserDelete(@RequestParam("id") Long id) {
        ResultUtil result = new ResultUtil();
        try {

                userService.deleteUserById(id);

            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public ResultUtil doBatchUserDelete(@RequestBody Long[] ids) {
        ResultUtil result = new ResultUtil();
        try {
            userService.piLiangDelete(ids);
            result.setSuccess(true);
            result.setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("删除失败，请重新操作");
        }
        return result;
    }


    /**
     * 跳转到用户新增页面
     *
     * @return
     */
    @RequestMapping("/add")
    public String toUserAdd() {
        return "user/userAdd";
    }

    /**
     * 处理用户新增
     *
     * @param user
     * @return
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public ResultUtil doUserAdd(User user) {
        ResultUtil result = new ResultUtil();
        try {
            userService.userAdd(user);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 跳转到个人中心页面
     *
     * @return
     */
    @RequestMapping("/profile/{id}")
    public String toProfile(@PathVariable Long id, Model model) {
        User dbUser = userService.findUserById(id);
        model.addAttribute("userInfo", dbUser);
        return "user/userProfile";
    }

    /**
     * 个人中心信息修改
     *
     * @param user
     * @return
     */
    @RequestMapping("/doProfileEdit")
    @ResponseBody
    public ResultUtil doUseProfileEdit(User user) {
        ResultUtil result = new ResultUtil();
        try {
            userService.profileEdit(user);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 跳转到用户密码修改页面
     *
     * @return
     */
    @RequestMapping("/changePassword/{id}")
    public String toUserChangePassword(@PathVariable("id") Long id, Model model) {
        User dbUser = userService.findUserById(id);
        model.addAttribute("userInfo", dbUser);
        return "user/changePassword";
    }


    /**
     * 处理用户密码修改
     *
     * @param user
     * @return
     */
    @RequestMapping("/doChangePassword")
    @ResponseBody
    public ResultUtil doChangePassword(User user) {
        ResultUtil result = new ResultUtil();
        try {
            userService.changePassword(user);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }



    @RequestMapping("/search")
    public String search(@RequestParam("type") String type,
                         @RequestParam("keyword") String keyword,
                         @RequestParam(name = "page", defaultValue = "1") Integer pageNum,
                         Model model) {
        PageHelper.startPage(pageNum, 5);
        List<User> users;
        if ("studentNumber".equals(type)) {
            users = userService.findUsersByStudentNumber(keyword);
        } else if ("realName".equals(type)) {
            users = userService.findUsersByRealName(keyword);
        } else {
            users = Collections.emptyList();
        }
        PageInfo<User> pageInfo = new PageInfo<>(users, 5);
        if (users.isEmpty()) {
            model.addAttribute("errorMessage", "查询错误，该用户不存在");
        } else {
            model.addAttribute("pageInfo", pageInfo);
        }
        return "user/userList";
    }


    @GetMapping("/checkStudentNumber")
    @ResponseBody
    public Map<String, Boolean> checkStudentNumber(@RequestParam String studentNumber) {
        List<User> users = userService.findUsersByStudentNumber(studentNumber);
        boolean exists = !users.isEmpty(); // 如果列表不为空，说明至少找到了一个用户，即学号已存在
        return Collections.singletonMap("exists", exists);
    }

    @GetMapping("/checkRealName")
    @ResponseBody
    public Map<String, Boolean> checkRealName(@RequestParam String realName) {
        List<User> users = userService.findUsersByRealName(realName);
        boolean exists = !users.isEmpty();
        return Collections.singletonMap("exists", exists);
    }


}
