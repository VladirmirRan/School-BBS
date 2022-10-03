package com.school.bbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.bbs.common.result.Result;
import com.school.bbs.modal.domain.User;
import com.school.bbs.modal.request.RegisterUser;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户注册服务层接口
 * @createDate 2022/10/3 11:25
 * @since 1.0.0
 */
public interface RegisterService extends IService<User> {

    /**
     * 用户注册接口
     *
     * @param userName      String 用户名称
     * @param password      String 用户密码
     * @param checkPassword String 用户确认密码
     * @param sex           String 用户性别
     * @param phone         String 用户电话
     * @param avatar        String 用户头像
     * @return Result 响应结果
     */
    Result register(String userName, String password, String checkPassword, String sex, String phone, String avatar);

}
