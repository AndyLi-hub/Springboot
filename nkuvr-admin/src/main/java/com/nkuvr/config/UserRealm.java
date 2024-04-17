package com.nkuvr.config;

import com.nkuvr.pojo.User;
import com.nkuvr.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: weizujie
 * @Date: 2020/5/7
 * @Version: 1.0
 * @Github: https://github.com/weizujie
 */


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------------------------------> 执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 获取当前登录的对象
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        info.addStringPermission(principal.getRemark());
        // 添加权限
        return info;
    }


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------------------------------> 执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByStudentNumber(token.getUsername());

        if (user == null) {
            // 可以直接抛出异常，由调用处捕获并设置错误信息
            throw new UnknownAccountException("用户不存在");
        }

        if (user.getState() == 1) {
            // 同上，抛出异常
            throw new LockedAccountException("用户被锁定!!!");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
