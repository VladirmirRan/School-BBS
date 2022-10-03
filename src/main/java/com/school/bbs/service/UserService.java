package com.school.bbs.service;

import com.school.bbs.controller.input.EditUserInfoInput;
import com.school.bbs.controller.output.UserInfoOutput;
import com.school.bbs.domain.UserDomain;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author why
 * @since 2022-10-03
 */
public interface UserService extends IService<UserDomain> {

    /**
     * 查下用户信息
     * @param id Integer
     * @return UserInfoOutput
     */
    UserInfoOutput getUserInfo(long id);

    /**
     * 编辑用户
     * @param id
     * @param role
     * @param input
     */
    void editUserInfo(Long id, Integer role, EditUserInfoInput input);
}
