package com.school.bbs.constant;

import lombok.Getter;

/**
 * @author why
 * @since 2022/10/11/15:20
 */
@Getter
public enum VerificationCodeEnum {
    /**
     * 手机号
     */
    PHONE(1, "手机号"),
    /**
     * 邮箱
     */
    MAIL(2, "邮箱");

    private final int code;

    private final String message;

    VerificationCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static VerificationCodeEnum getByCode(Integer code) {
        for (VerificationCodeEnum value : VerificationCodeEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }


}
