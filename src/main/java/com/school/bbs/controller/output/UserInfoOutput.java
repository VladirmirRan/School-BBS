package com.school.bbs.controller.output;

import com.school.bbs.constant.RoleEnum;
import com.school.bbs.constant.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author why
 * @since 2022/10/03/18:02
 */
@Data
@ApiModel("用户详情出参")
public class UserInfoOutput {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("角色：1后台管理员、2管理员、3用户")
    private RoleEnum role;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("性别 1男 2女")
    private SexEnum sex;

    @ApiModelProperty("头像")
    private String avatar;
}
