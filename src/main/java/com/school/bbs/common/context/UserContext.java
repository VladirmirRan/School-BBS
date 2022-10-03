package com.school.bbs.common.context;

import java.util.Date;

/**
 * @author why
 * @date 2022/10/03/10:32
 */
public class UserContext {
    private static final long serialVersionUID = -6592741745554468326L;
    private long id;
    private String name;
    private boolean enable;
    private int status;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
