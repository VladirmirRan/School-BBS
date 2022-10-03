package com.school.bbs.controller.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author why
 * @date 2022/10/03/16:19
 */
@Data
@ApiModel("用户登录出参")
public class LoginOutput {
    @ApiModelProperty("用户token")
    private String jwtToken;

    public LoginOutput(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
