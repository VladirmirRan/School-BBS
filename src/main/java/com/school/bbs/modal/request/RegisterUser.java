package com.school.bbs.modal.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户注册封装类
 * @createDate 2022/10/3 11:55
 * @since 1.0.0
 */
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
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
