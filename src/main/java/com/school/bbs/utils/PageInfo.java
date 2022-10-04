package com.school.bbs.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页参数")
public class PageInfo {
    @ApiModelProperty("分页大小")
    private long pageSize;

    @ApiModelProperty("页数")
    private long pageNum;

    public long getPageSize() {
        return pageSize;
    }

    public long getPageNum() {
        return pageNum;
    }
}
