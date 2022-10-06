package com.school.bbs.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author why
 * @since 2022-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article")
public class ArticleDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章内容
     */
    @TableField("content")
    private String content;

    /**
     * 文章摘要
     */
    @TableField("summary")
    private String summary;

    /**
     * 所属分类id
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 缩略图
     */
    @TableField("thumbnail")
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

    /**
     * 是否允许评论 1是，0否
     */
    @TableField("comment")
    private Boolean comment;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_by")
    private Long updateBy;


}
