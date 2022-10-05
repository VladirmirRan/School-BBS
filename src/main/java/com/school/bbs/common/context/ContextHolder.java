package com.school.bbs.common.context;

import com.alibaba.fastjson.JSON;
import com.school.bbs.common.exception.YyghException;
import com.school.bbs.common.result.ResultCodeEnum;
import com.school.bbs.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @author why
 * @date 2022/10/03/10:30
 */
public class ContextHolder {

    public static UserContext getLoginContext() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String authorization = requestAttributes.getRequest().getHeader("token");
        try {
            Claims claims = JwtUtil.parseJWT(authorization);
            UserContext userContext = JSON.parseObject(claims.getSubject(), UserContext.class);
            return userContext;
        } catch (Exception e) {
            throw new YyghException(ResultCodeEnum.DATA_ERROR);
        }

    }

    public static LoginContext getLoginBeforeContext() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String authorization = requestAttributes.getRequest().getHeader("token");
        try {
            Claims claims = JwtUtil.parseJWT(authorization);
            LoginContext loginContext = JSON.parseObject(claims.getSubject(), LoginContext.class);
            return loginContext;
        } catch (Exception e) {
            throw new YyghException(ResultCodeEnum.DATA_ERROR);
        }

    }
}

