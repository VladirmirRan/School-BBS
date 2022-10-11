package com.school.bbs.controller.input;

import com.school.bbs.constant.VerificationCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author why
 * @since 2022/10/11/15:17
 */
@Data
@ApiModel("用户获取验证码入参")
public class GetVerificationCodeInput {
    @ApiModelProperty("验证码类型")
    private VerificationCodeEnum codeEnum;

    @ApiModelProperty("内容")
    private String content;
}
