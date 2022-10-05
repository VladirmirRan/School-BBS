package com.school.bbs.controller;

import com.school.bbs.common.result.ApiResult;
import com.school.bbs.service.RegisterService;
import com.school.bbs.utils.request.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户注册接口
 * @createDate 2022/10/3 11:24
 * @since 1.0.0
 */
@ApiResult
@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 用户注册接口
     *
     * @param registerUser RegisterUser
     * @return Result
     */
    @PostMapping("/register")
    public void register(@RequestBody RegisterUser registerUser) {
        registerService.register(registerUser.getName(), registerUser.getPassword(), registerUser.getCheckPassword(), registerUser.getSex(), registerUser.getPhone(), registerUser.getAvatar(), registerUser.getUuid());
    }

}
