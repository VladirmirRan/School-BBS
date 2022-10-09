package com.school.bbs.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author why
 * @since 2022-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oss_client")
public class OssClientDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 客户端注册名称
     */
    @TableField("name")
    private String name;

    /**
     * 客户端唯一标识
     */
    @TableField("client_id")
    private String clientId;




}
