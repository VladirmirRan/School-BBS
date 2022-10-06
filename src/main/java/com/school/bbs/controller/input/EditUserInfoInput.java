package com.school.bbs.controller.input;

import com.school.bbs.constant.RoleEnum;
import com.school.bbs.constant.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author why
 * @since 2022/10/03/19:41
 */
@Data
@ApiModel("编辑用户入参")
public class EditUserInfoInput {

    @ApiModelProperty(value = "主键",required = true)
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("性别")
    private SexEnum sex;

    @ApiModelProperty("头像")
    private String avatar;

}
