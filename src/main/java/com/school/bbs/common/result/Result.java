package com.school.bbs.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.school.bbs.constant.errorCode.ErrorInfo;
import com.school.bbs.constant.errorCode.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("全局返回格式")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> extends IResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("返回状态码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private String msg;
    @ApiModelProperty("返回数据")
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }

    public static <T> Result<T> build(T data, Integer code,String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    /**
     * 操作失败
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL.getCode(),ResultCodeEnum.FAIL.getMessage());
    }

    /**
     * 操作失败
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> Result<T> fail(Integer code,String message) {
        return build(null, code,message);
    }

    /**
     * 操作失败
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail() {
        return build(null, ResultCodeEnum.FAIL.getCode(),ResultCodeEnum.FAIL.getMessage());
    }

}
