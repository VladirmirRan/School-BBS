package com.school.bbs.controller.input;

import com.school.bbs.utils.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 查询帖子评论入参
 * @createDate 2022/10/10 22:07
 * @since 1.0.0
 */
@Data
@ToString
@ApiModel("查询评论列表入参")
public class QueryCommentListInput extends PageInfo {

    /**
     * 文章编号
     */
    @ApiModelProperty("帖子编号")
    private Long articleId;

}
