package com.school.bbs.controller.input;

import com.school.bbs.utils.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author why
 * @since 2022/10/04/23:00
 */
@Data
@ToString
@ApiModel("查询文章列表入参")
public class QueryArticleInput extends PageInfo {
    @ApiModelProperty("文章标题")
    private String title;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;

}
