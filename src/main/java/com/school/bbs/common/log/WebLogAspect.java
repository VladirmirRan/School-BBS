package com.school.bbs.common.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.bbs.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * 切面处理，记录入参、出参、异常信息
 *
 * @author why
 * @since 2022/10/08/15:42
 */
@Aspect
@Slf4j
@Component
public class WebLogAspect {
    /**
     * aop 签名
     */
    @Pointcut("execution(* com.school.bbs.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 切面处理--->>>事前处理
     *
     * @param point JoinPoint 联机对象
     */
    @Before("webLog()")
    public void doBefore(JoinPoint point) {
        //收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("请求METHOD，URL : {}，{}", request.getMethod(), request.getRequestURL());
        log.info("请求IP :{} ", IpUtil.getIpAddress(request));
        log.info("请求处理CLASS_METHOD : {}", point.getSignature().getDeclaringTypeName() + "."
                + point.getSignature().getName());
        log.info("请求参数：{} : " + Arrays.toString(point.getArgs()));

    }

    /**
     * 切面--->>>异常处理
     *
     * @param point     JoinPoint
     * @param exception Throwable
     */
    @AfterThrowing(throwing = "exception", pointcut = "webLog()")
    public void doRecoveryAction(JoinPoint point, Throwable exception) {
        String className = point.getTarget().getClass().getSimpleName()+"["+point.getSignature().getName()+"]";
        log.error("[结果返回异常]"+className+" :返回"+exception);
    }

    /**
     * 切面--->>>事后处理
     * @param res Object
     * @throws JsonProcessingException
     */
    @AfterReturning(returning = "res",pointcut = "webLog()")
    public void doAfterReturning(Object res) throws JsonProcessingException{
        //获取返回值
        log.info("返回值："+new ObjectMapper().writeValueAsString(res));
    }
}