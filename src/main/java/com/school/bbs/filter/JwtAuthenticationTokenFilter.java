package com.school.bbs.filter;

import com.alibaba.fastjson.JSON;
import com.school.bbs.common.context.UserContext;
import com.school.bbs.constant.AuthConstant;
import com.school.bbs.utils.JwtUtil;
import com.school.bbs.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description Jwt认证过滤器
 * @createDate 2022/10/3 9:48
 * @since 1.0.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");

        //todo 不带token自动放行？
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token获取其中的userid
        UserContext userContext;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userContext = JSON.parseObject(claims.getSubject(), UserContext.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = AuthConstant.LOGIN + userContext.getId();
        UserContext context = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(context)) {
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(context, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
