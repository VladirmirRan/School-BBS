package com.school.bbs.service;

import com.school.bbs.controller.input.LoginInput;
import com.school.bbs.controller.output.LoginOutput;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 服务层登录接口
 * @createDate 2022/10/2 13:30
 * @since 1.0.0
 */
public interface LoginService {

    /**
     * 登录接口
     *
     * @param user User
     * @return Result
     */
    LoginOutput login(LoginInput user);

    /**
     * 登出接口
     */
    void logout();

}
