package com.school.bbs.utils.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户注册封装类
 * @createDate 2022/10/3 11:55
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser implements Serializable {

    private static final long serialVersionUID = 5087877986705716580L;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户确认密码
     */
    private String checkPassword;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * uuid
     */
    private String uuid;
}
