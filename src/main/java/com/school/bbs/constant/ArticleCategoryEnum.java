package com.school.bbs.constant;

/**
 * @author why
 * @since 2022/10/06/11:39
 */
public enum ArticleCategoryEnum {
    /**
     * 表白墙
     */
    CONFESSION_WALL(1,"表白墙"),;

    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private ArticleCategoryEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ArticleCategoryEnum getByCode(Integer code) {
        for (ArticleCategoryEnum value : ArticleCategoryEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
