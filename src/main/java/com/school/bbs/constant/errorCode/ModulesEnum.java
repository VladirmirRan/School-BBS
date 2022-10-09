package com.school.bbs.constant.errorCode;

/**
 * @author why
 * @since 2022/10/08/10:58
 */
public enum ModulesEnum {
    /**
     * 系统模块
     */
    SYSTEM(500,"系统模块"),
    /**
     * 帖子模块
     */
    OSS(600,"帖子模块"),
    /**
     * 用户模块
     */
    USER(610, "用户错误代码"),
    /**
     * 帖子模块
     */
    ARTICLE(620,"帖子模块"),

    ;
    private Integer code;

    private String moduleName;

    public Integer getCode() {
        return code;
    }

    public String getModuleName() {
        return moduleName;
    }

    ModulesEnum(Integer code, String moduleName) {
        this.code = code;
        this.moduleName = moduleName;
    }
}
