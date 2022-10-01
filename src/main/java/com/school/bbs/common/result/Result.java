package com.school.bbs.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    public static <T> Result<T> build(T data, ResultCodeEnum codeEnum) {
        Result result = new Result();
        result.setCode(codeEnum.getCode());
        result.setMsg(codeEnum.getMessage());
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
        return build(data, ResultCodeEnum.FAIL);
    }

    /**
     * 操作失败
     *
     * @param codeEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(ResultCodeEnum codeEnum) {
        return build(null, codeEnum);
    }

    /**
     * 操作失败
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

}
