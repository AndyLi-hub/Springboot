package com.nkuvr.dao;

import com.nkuvr.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: weizujie
 * @Date: 2020/4/25
 * @Version: 1.0
 * @Github: https://github.com/weizujie
 */
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 根据 id 查询用户
     *
     * @param id
     * @return
     */
    User findUserById(@Param("id") Long id);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 根据 id 删除用户
     *
     * @param id
     */
    void deleteUserById(@Param("id") Long id);

    /**
     * 管理员对用户进行编辑
     *
     * @param user
     */
    void userEdit(User user);

    /**
     * 管理员对用户进行新增
     *
     * @param users
     */
    void userAdd(User users);

    /**
     * 用户注册
     *
     * @param user
     */
    void register(User user);

    /**
     * 用户个人信息修改
     *
     * @param user
     */
    void profileEdit(User user);

    /**
     * 用户密码修改
     *
     * @param user
     */
    void changePassword(User user);

    /**
     * 根据学号查询用户
     *
     */
    User findUserByStudentNumber(@Param("studentNumber") String studentNumber);

    /**
     * 根据学号 姓名查询用户
     */
    List<User> findUsersByStudentNumber(@Param("studentNumber") String studentNumber1);

    List<User> findUsersByRealName(@Param("realName") String realName);

    /**
     * piliang delete
     */
    void piLiangDelete(@Param("ids") Long[] ids);
}
