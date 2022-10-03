package com.school.bbs.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author why
 * @since 2022-10-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class UserDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("name")
    private String name;

    /**
     * 角色：1后台管理员、2管理员、3用户
     */
    @TableField("role")
    private Integer role;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别 1男 2女
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 创建人id
     */
    @TableField("create_user")
    private Long createUser;


}
