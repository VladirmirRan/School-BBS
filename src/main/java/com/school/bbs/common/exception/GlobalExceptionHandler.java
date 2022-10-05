package com.school.bbs.common.exception;


import com.school.bbs.common.result.Result;
import com.school.bbs.constant.ResultCodeEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Nier_2B
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<T> error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result<T> error(YyghException e) {
        e.printStackTrace();
        return Result.fail(ResultCodeEnum.getByCode(e.getCode()));
    }
}
