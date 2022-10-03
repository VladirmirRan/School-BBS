package com.school.bbs.converter;

import com.school.bbs.constant.RoleEnum;
import com.school.bbs.constant.SexEnum;
import com.school.bbs.controller.input.EditUserInfoInput;
import com.school.bbs.controller.output.UserInfoOutput;
import com.school.bbs.domain.UserDomain;

/**
 * @author why
 * @since 2022/10/03/18:48
 */
public class UserConvert {
    public static UserInfoOutput toUserInfo(UserDomain userDomain) {
        UserInfoOutput output = new UserInfoOutput();
        output.setId(userDomain.getId());
        output.setName(userDomain.getName());
        output.setRole(RoleEnum.getByCode(userDomain.getRole()));
        output.setPhone(userDomain.getPhone());
        output.setSex(SexEnum.getByCode(userDomain.getSex()));
        output.setAvatar(userDomain.getAvatar());
        return output;
    }


    public static UserDomain toEditUserInfo(UserDomain userDomain, EditUserInfoInput input) {
        String name = input.getName();
        String phone = input.getPhone();
        SexEnum sex = input.getSex();
        String avatar = input.getAvatar();
        userDomain.setAvatar(avatar);
        userDomain.setSex(sex.getCode());
        userDomain.setPhone(phone);
        userDomain.setName(name);
        return userDomain;


    }
}
