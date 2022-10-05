package com.school.bbs.constant;

/**
 * @author why
 * @since 2022/10/05/20:43
 */
public enum DeletedEnum {
    /**
     * 未删除
     */
    UNDELETED(false, "未删除"),
    /**
     * 已删除
     */
    DELETED(true, "已删除"),
    ;

    private final boolean code;
    private final String message;

    public boolean getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private DeletedEnum(boolean code, String message) {
        this.code = code;
        this.message = message;
    }

    public static DeletedEnum getByCode(boolean code) {
        for (DeletedEnum value : DeletedEnum.values()) {
            if (value.getCode() == (code)) {
                return value;
            }
        }
        return null;
    }
}
