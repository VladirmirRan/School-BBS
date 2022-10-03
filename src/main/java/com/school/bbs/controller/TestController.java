package com.school.bbs.controller;

import com.school.bbs.common.context.ContextHolder;
import com.school.bbs.common.result.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 测试控制类
 * @createDate 2022/10/2 10:34
 * @since 1.0.0
 */
@ApiResult
@RestController
public class TestController {

    @PostMapping("/hello")
    public String hello() {
        return ContextHolder.getLoginContext().getName().toString();
    }

}
