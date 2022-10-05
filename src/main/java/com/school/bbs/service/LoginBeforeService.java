package com.school.bbs.service;

import com.school.bbs.controller.output.LoginBeforeOutput;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户登录前服务层接口
 * @createDate 2022/10/5 12:14
 * @since 1.0.0
 */
public interface LoginBeforeService {

    /**
     * 用户登陆前生成密钥对
     *
     * @return LoginBeforeOutput 出参
     */
    LoginBeforeOutput loginBefore();

}
