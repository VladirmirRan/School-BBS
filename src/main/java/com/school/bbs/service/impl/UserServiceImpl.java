package com.school.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.bbs.common.exception.YyghException;
import com.school.bbs.constant.errorCode.ResultCodeEnum;
import com.school.bbs.constant.RoleEnum;
import com.school.bbs.controller.input.EditUserInfoInput;
import com.school.bbs.controller.output.UserInfoOutput;
import com.school.bbs.converter.UserConvert;
import com.school.bbs.domain.UserDomain;
import com.school.bbs.mapper.UserDomainMapper;
import com.school.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author why
 * @since 2022-10-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDomainMapper, UserDomain> implements UserService {
    @Autowired
    private UserDomainMapper userMapper;

    @Override
    public UserInfoOutput getUserInfo(long id) {
        QueryWrapper<UserDomain> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        UserDomain userDomain = userMapper.selectOne(wrapper);
        return UserConvert.toUserInfo(userDomain);
    }

    @Override
    public void editUserInfo(Long id, Integer role, EditUserInfoInput input) {
        QueryWrapper<UserDomain> wrapper = new QueryWrapper<>();
        wrapper.eq("id", input.getId());
        UserDomain userDomain = userMapper.selectOne(wrapper);
        userDomain = UserConvert.toEditUserInfo(userDomain, input);
        //自己改自己
        if (input.getId().equals(id)) {
            userMapper.updateById(userDomain);
        }
        //当前操作人为管理员
        else if (RoleEnum.USER.getCode() != role) {
            //如果当前登录用户为二级管理员则只能修改用户
            if (RoleEnum.SECOND_ADMIN.getCode() == role && RoleEnum.USER.getCode() == userDomain.getRole()) {
                userMapper.updateById(userDomain);
            }//如果是系统管理员则所有用户均可修改
            else if (RoleEnum.FIRST_ADMIN.getCode() == role) {
                userMapper.updateById(userDomain);
            }
        } else {
            throw new YyghException(ResultCodeEnum.PERMISSION);
        }


    }


}
