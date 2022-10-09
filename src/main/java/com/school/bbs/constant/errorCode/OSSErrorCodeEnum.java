package com.school.bbs.constant.errorCode;

/**
 * @author why
 * @since 2022/10/08/17:46
 */
public enum OSSErrorCodeEnum implements ErrorInfo {
    REQUEST_NOT_FILE(101, "请求必须是文件格式"),

    REQUEST_FILE_EMPTY(102, "请求文件列表为空"),
    UPLOAD_FILE_ERROR(103, "文件上传错误"),
    FILE_FORMAT_ERROR(104, "上传文件格式非法，请检查文件"),
    FILE_NAME_TOO_LONG(105, "文件名称过长"),
    FILE_NAME_ERROR(106, "请检查文件名称格式，不能包含\\/:*?\"<>|% $以及空格"),
    CLIENT_ID_NOT_FOUND(107, "ClientId 不存在");


    private final ModulesEnum modulesEnum = ModulesEnum.OSS;
    private final Integer code;
    private final String message;

    OSSErrorCodeEnum(Integer code, String message) {
        this.code = modulesEnum.getCode() * ErrorCodeConstants.MODULE_LEVEL + code;
        this.message = message;
    }


    public Integer getCode() {
        return this.code;
    }


    public ModulesEnum getModulesEnum() {
        return this.modulesEnum;
    }


    public String getMessage() {
        return message;
    }
}
