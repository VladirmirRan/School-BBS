package com.school.bbs.constant.errorCode;

/**
 * 统一返回结果状态信息类
 * @author Nier_2B
 */

public enum ResultCodeEnum implements ErrorInfo{

    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    DATA_ERROR(204, "数据异常"),
    PERMISSION(209, "没有权限"),
    ;

    private final ModulesEnum modulesEnum = ModulesEnum.USER;

    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ModulesEnum getModulesEnum() {
        return modulesEnum;
    }

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultCodeEnum getByCode(Integer code) {
        for (ResultCodeEnum value : ResultCodeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
