package com.school.bbs.constant;

import lombok.Getter;

/**
 * @author why
 * @since 2022/10/11/17:26
 */
@Getter
public enum LoginTypeEnum {

    /**
     * 手机号
     */
    LOGIN_PHONE(1, "手机号"),
    /**
     * 邮箱
     */
    LOGIN_MAIL(2, "邮箱");

    private final int code;

    private final String message;

    LoginTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static LoginTypeEnum getByCode(Integer code) {
        for (LoginTypeEnum value : LoginTypeEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
