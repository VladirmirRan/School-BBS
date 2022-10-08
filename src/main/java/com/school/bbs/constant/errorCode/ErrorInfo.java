package com.school.bbs.constant.errorCode;

/**
 * @author why
 * @since 2022/10/08/14:01
 */
public interface ErrorInfo {
    /**
     *获取错误码
     * @return
     */
    Integer getCode();

    /**
     * 获取错误码描述
     * @return
     */
    ModulesEnum getModulesEnum();

    /**
     * 获取错误描述
     *
     * @return
     */
    String getMessage();
}
