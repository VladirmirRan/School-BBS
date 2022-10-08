package com.school.bbs.constant.errorCode;


import static com.school.bbs.constant.errorCode.ErrorCodeConstants.MODULE_LEVEL;

/**
 * @author why
 * @since 2022/10/08/11:06
 */
public enum UserCodeEnum implements  ErrorInfo{
    USERNAME_OR_PASSWORD_ERROR(501, "账号或密码错误"),
    USER_DELETED(502, "用户已被删除"),
    USER_DISABLE(503, "用户已被禁用"),
    USER_NOT_EXIST(504, "用户不存在"),

    USERNAME_NOT_NULL(507, "用户名不能为空"),
    TOKEN_ERROR(508, "token非法"),
    USER_NOT_LOGIN(509, "用户未登录"),
    PASSWORD_NOT_NULL(517, "密码不能为空"),
    PHONE_NOT_NULL(527, "电话不能为空"),
    SEX_NOT_NULL(537, "性别不能为空"),
    AVATAR_NOT_NULL(547, "头像不能为空"),
    USERNAME_EXIST(567, "用户名已存在"),
    USER_REGISTER_FAIL(577, "用户注册失败"),
    PASSWORD_LENGTH_EIGHT(587, "密码长度小于8位"),
    UUID_NOT_NULL(597, "UUID不能为空"),
    PASSWORD_NOT_MATCH(598, "确认密码输入不一致"),;

    private ModulesEnum modulesEnum = ModulesEnum.USER;
    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public ModulesEnum getModulesEnum() {
        return modulesEnum;
    }

    public String getMessage() {
        return message;
    }

    UserCodeEnum(Integer code, String msg) {
        this.code = modulesEnum.getCode()*MODULE_LEVEL+code;
        this.message = msg;
    }
}
