package com.school.bbs.common.result;

import com.school.bbs.constant.errorCode.ResultCodeEnum;

import java.lang.annotation.*;

import static com.school.bbs.constant.errorCode.ResultCodeEnum.SUCCESS;

/**
 * @author Nier_2B
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiResult {
    String value() default "";

    ResultCodeEnum codeEnum() default SUCCESS;

    Class<? extends IResult> resultClass() default Result.class;
}
