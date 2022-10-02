package com.school.bbs.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户实体类
 * @createDate 2022/10/2 11:59
 * @since 1.0.0
 */
@ApiModel("用户实体类")
@TableName(value = "user")
public class User implements Serializable {

    /**
     * 用户编号
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("用户编号")
    private Long id;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String name;

    /**
     * 用户角色：1 - 后台管理员、2 - 管理员、3 - 用户
     */
    @ApiModelProperty("用户角色")
    private int role;

    /**
     * 用户电话
     */
    @ApiModelProperty("用户电话")
    private String phone;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;

    /**
     * 用户性别
     */
    @ApiModelProperty("用户性别")
    private String sex;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 创建人编号
     */
    @ApiModelProperty("创建人编号")
    private String createUser;

    /**
     * 用户状态：0 - 禁用、1 - 启用
     */
    @ApiModelProperty("用户状态")
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private String updateTime;

    /**
     * 是否删除：0 - 未删除、1 - 删除
     */
    @TableLogic
    @ApiModelProperty("是否删除")
    private String deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public User() {
    }

    public User(Long id, String name, int role, String phone, String password, String sex, String avatar, String createUser, String status, String createTime, String updateTime, String deleted) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.password = password;
        this.sex = sex;
        this.avatar = avatar;
        this.createUser = createUser;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createUser='" + createUser + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
