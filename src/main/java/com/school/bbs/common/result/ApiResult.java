package com.school.bbs.common.result;

import java.lang.annotation.*;

import static com.school.bbs.common.result.ResultCodeEnum.SUCCESS;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiResult {
    String value() default "";

    ResultCodeEnum codeEnum() default SUCCESS;

    Class<? extends IResult> resultClass() default Result.class;
}
