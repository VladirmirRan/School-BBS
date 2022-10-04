package com.school.bbs.controller.output;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author why
 * @since 2022/10/04/22:59
 */
@Data
@ApiModel("查找文章列表出参")
public class QueryArticleListOutput {
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "所属分类id", required = true)
    private Long categoryId;


    @ApiModelProperty("缩略图")
    private String thumbnail;

    /**
     * 是否置顶（0否，1是）
     */
    @TableField("is_top")
    private Boolean isTop;

    /**
     * 访问量
     */
    @TableField("view_count")
    private Long viewCount;

}
