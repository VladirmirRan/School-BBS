package com.school.bbs.controller.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户登录前出参
 * @createDate 2022/10/5 12:11
 * @since 1.0.0
 */
@Data
@ApiModel("用户登录前出参")
public class LoginBeforeOutput {
    /**
     * uuid
     */
    @ApiModelProperty("uuid")
    private String uuid;

    /**
     * 公钥
     */
    @ApiModelProperty("公钥")
    private String publicKey;

}
