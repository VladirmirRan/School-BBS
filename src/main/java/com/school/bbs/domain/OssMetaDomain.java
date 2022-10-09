package com.school.bbs.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author why
 * @since 2022-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oss_meta")
public class OssMetaDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 文件所属人
     */
    @TableField("client_id")
    private String clientId;

    /**
     * 文件名称
     */
    @TableField("name")
    private String name;

    /**
     * 文件大小
     */
    @TableField("size")
    private Long size;

    /**
     * 文件相对目录地址
     */
    @TableField("uri")
    private String uri;

    /**
     * 文件Hash指纹
     */
    @TableField("md5")
    private String md5;

    /**
     * 媒体类型
     */
    @TableField("media_type")
    private String mediaType;




}
