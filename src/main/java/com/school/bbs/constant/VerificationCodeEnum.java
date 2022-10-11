package com.school.bbs.constant;

import lombok.Getter;

/**
 * @author why
 * @since 2022/10/11/15:20
 */
@Getter
public enum VerificationCodeEnum {
    /**
     * 男
     */
    PHONE(1),
    /**
     * 女
     */
    MAIL(2);

    private final int code;

    VerificationCodeEnum(Integer code) {
        this.code = code;
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
