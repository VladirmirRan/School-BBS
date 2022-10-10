package com.school.bbs.controller.output;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 查询评论列表出参
 * @createDate 2022/10/10 22:20
 * @since 1.0.0
 */
@Data
@ApiModel("查找文章列表出参")
public class QueryCommentListOutput {

    /**
     * 评论编号
     */
    @ApiModelProperty("评论编号")
    private Long commentId;

    /**
     * 文章编号
     */
    @ApiModelProperty("文章编号")
    private Long articleId;

    /**
     * 根评论编号
     */
    @ApiModelProperty("根评论编号")
    private Long rootId;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String content;

    /**
     * 所回复的目标评论的用户编号
     */
    @ApiModelProperty("所回复的目标评论的用户编号")
    private Long toCommentUserId;

    /**
     * 回复目标评论编号
     */
    @ApiModelProperty("回复目标评论编号")
    private Long toCommentId;

    /**
     * 创建者编号
     */
    @ApiModelProperty("创建者编号")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;

    /**
     * 子评论集合
     */
    @ApiModelProperty("子评论集合")
    private List<QueryCommentListOutput> children;


}
