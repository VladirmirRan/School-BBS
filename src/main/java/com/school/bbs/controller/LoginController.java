package com.school.bbs.controller;

import com.school.bbs.common.result.ApiResult;
import com.school.bbs.controller.input.LoginInput;
import com.school.bbs.controller.output.LoginOutput;
import com.school.bbs.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("用户登录相关类")
@ApiResult
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录接口
     * @param loginInput 入参
     * @return loginOutput 出参
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public LoginOutput login(@RequestBody LoginInput loginInput) {
        //登录
        return loginService.login(loginInput);
    }

    /**
     * 登出接口
     */
    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public void logout() {
        //登出
        loginService.logout();
    }

}
