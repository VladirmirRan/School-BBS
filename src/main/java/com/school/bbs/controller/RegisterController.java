package com.school.bbs.controller;

import com.school.bbs.common.result.Result;
import com.school.bbs.common.result.ResultCodeEnum;
import com.school.bbs.modal.domain.User;
import com.school.bbs.modal.request.RegisterUser;
import com.school.bbs.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
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
    public Result register(@RequestBody RegisterUser registerUser) {
        if (registerUser == null) {
            return new Result(ResultCodeEnum.USER_REGISTER_FAIL.getCode(), ResultCodeEnum.USER_REGISTER_FAIL.getMessage());
        }
        return registerService.register(registerUser.getName(), registerUser.getPassword(), registerUser.getCheckPassword(), registerUser.getSex(), registerUser.getPhone(), registerUser.getAvatar());
    }

}
