package com.school.bbs.constant;

import lombok.Getter;

/**
 * @author why
 * @since 2022/10/03/18:09
 */
@Getter
public enum SexEnum {
    /**
     * 男
     */
    MAN(1),
    /**
     * 女
     */
    WOMAN(2);

    private final int code;

    SexEnum(Integer code) {
        this.code = code;
    }

    public static SexEnum getByCode(Integer code){
        for (SexEnum value : SexEnum.values()) {
            if(value.getCode()==code){
                return value;
            }
        }
        return null;
    }

}
