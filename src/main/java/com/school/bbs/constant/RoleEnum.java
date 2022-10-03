package com.school.bbs.constant;

import lombok.Getter;

/**
 * @author why
 * @since 2022/10/03/18:16
 */
@Getter
public enum RoleEnum {
    /**
     * 主管理员
     */
    FIRST_ADMIN(1, "主管理员"),
    /**
     * 管理员
     */
    SECOND_ADMIN(2, "管理员"),
    /**
     * 用户
     */
    USER(3, "用户");

    private final int code;
    private final String msg;

    RoleEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static RoleEnum getByCode(Integer code) {
        for (RoleEnum value : RoleEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }


}
