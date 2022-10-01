package com.school.bbs.common.result;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Order(0)
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    protected boolean isApiResult(MethodParameter returnType) {
        return returnType.getContainingClass().isAnnotationPresent(ApiResult.class);
    }
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return isApiResult(returnType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        ApiResult apiResult = returnType.getMethod().getAnnotation(ApiResult.class);
        if (body instanceof String) {
            return JSONObject.toJSONString(Result.build(body,apiResult.codeEnum()));
        }
        if (apiResult == null) {
            return Result.build(body, ResultCodeEnum.SUCCESS);
        }
        return Result.build(body, apiResult.codeEnum());
    }

}
