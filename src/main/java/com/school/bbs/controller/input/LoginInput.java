package com.school.bbs.controller.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author why
 * @date 2022/10/03/16:16
 */
@Data
@ApiModel("用户登录入参")
public class LoginInput {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String usrName;

    /**
     * 密码
     */
    @ApiModelProperty("用户密码")
    private String password;


}
