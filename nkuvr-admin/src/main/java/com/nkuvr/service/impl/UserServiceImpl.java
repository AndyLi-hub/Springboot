package com.nkuvr.service.impl;

import com.nkuvr.dao.UserMapper;
import com.nkuvr.pojo.User;
import com.nkuvr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: weizujie
 * @Date: 2020/4/25
 * @Version: 1.0
 * @Github: https://github.com/weizujie
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     * 根据 id 查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    /**
     * 根据 id 删除用户
     *
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteUserById(id);
    }


    /**
     * 管理员对用户进行编辑
     *
     * @param user
     */
    @Override
    public void userEdit(User user) {
        userMapper.userEdit(user);
    }

    @Override
    public void userAdd(User users) {
        userMapper.userAdd(users);
    }

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    /**
     * 用户个人信息修改
     *
     * @param user
     */
    @Override
    public void profileEdit(User user) {
        userMapper.profileEdit(user);
    }

    /**
     * 用户密码修改
     *
     * @param user
     */
    @Override
    public void changePassword(User user) {
        userMapper.changePassword(user);
    }

    /**
     * 根据学号查询用户
     *
     * @param studentNumber
     * @return
     */
    @Override
    public User findUserByStudentNumber(String studentNumber) {
        return userMapper.findUserByStudentNumber(studentNumber);
    }

    @Override
    public List<User> findUsersByStudentNumber(String studentNumber) {
        return userMapper.findUsersByStudentNumber(studentNumber);
    }

    @Override
    public List<User> findUsersByRealName(String realName) {
        return userMapper.findUsersByRealName(realName);
    }

    @Override
    public void piLiangDelete(Long[] ids) {
        userMapper.piLiangDelete(ids);
    }


}
