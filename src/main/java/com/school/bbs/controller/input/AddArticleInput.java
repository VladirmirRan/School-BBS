package com.school.bbs.controller.input;

import com.school.bbs.constant.ArticleCategoryEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author why
 * @since 2022/10/04/20:32
 */
@Data
@ApiModel("添加帖子入参")
public class AddArticleInput {

    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "文章内容", required = true)
    private String content;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "所属分类枚举", required = true)
    private ArticleCategoryEnum categoryEnum;


    @ApiModelProperty("缩略图")
    private String thumbnail;


}
