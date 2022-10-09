package com.school.bbs.controller.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author why
 * @since 2022/10/08/17:40
 */
@ApiModel("文件上传出参")
@Data
public class FileUploadOutput {

    @ApiModelProperty("文件Id")
    private long fileId;


}
