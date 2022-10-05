package com.school.bbs.controller;

import com.school.bbs.common.result.ApiResult;
import com.school.bbs.controller.output.LoginBeforeOutput;
import com.school.bbs.service.LoginBeforeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lu.xin
 * @version 1.0.0
 * @description 登录前生成密钥对接口
 * @createDate 2022/10/5 11:49
 * @since 1.0.0
 */
@Api("用户登录前相关类")
@ApiResult
@RestController
@RequestMapping("/login")
public class LoginBeforeController {

    @Autowired
    private LoginBeforeService loginBeforeService;

    @ApiOperation("用户登录前接口")
    @GetMapping("/before")
    public LoginBeforeOutput loginBefore() {
        return loginBeforeService.loginBefore();
    }

}
