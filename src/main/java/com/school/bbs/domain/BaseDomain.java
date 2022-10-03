package com.school.bbs.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author Nier_2B
 */
@Data
public class BaseDomain {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 状态：1、可用2、不可用
     */
    @TableField(value = "status", fill = FieldFill.INSERT)
    private Boolean status;

    @TableField("deleted")
    @TableLogic
    private Boolean deleted = false;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
