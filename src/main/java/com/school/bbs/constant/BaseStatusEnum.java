package com.school.bbs.constant;

/**
 * @author why
 * @since 2022/10/05/20:49
 */
public enum BaseStatusEnum {
    /**
     * 启用
     */
    ENABLE(true, "启用"),
    /**
     * 禁用
     */
    DISABLE(false, "禁用"),
    ;

    private final boolean code;
    private final String message;

    public boolean getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private BaseStatusEnum(boolean code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseStatusEnum getByCode(boolean code) {
        for (BaseStatusEnum value : BaseStatusEnum.values()) {
            if (value.getCode() == (code)) {
                return value;
            }
        }
        return null;
    }
}
