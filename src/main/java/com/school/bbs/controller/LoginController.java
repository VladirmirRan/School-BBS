package com.school.bbs.controller;

import com.school.bbs.common.result.Result;
import com.school.bbs.modal.domain.User;
import com.school.bbs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 登录接口
 * @createDate 2022/10/2 13:26
 * @since 1.0.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录接口
     *
     * @param user User
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        //登录
        return loginService.login(user);
    }

    /**
     * 登出接口
     *
     * @return Result
     */
    @PostMapping("/logout")
    public Result logout() {
        //登出
        return loginService.logout();
    }

}
