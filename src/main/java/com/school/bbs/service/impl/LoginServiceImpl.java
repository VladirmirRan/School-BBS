package com.school.bbs.service.impl;

import com.alibaba.fastjson.JSON;
import com.school.bbs.constant.AuthConstant;
import com.school.bbs.controller.input.LoginInput;
import com.school.bbs.controller.output.LoginOutput;
import com.school.bbs.service.LoginService;
import com.school.bbs.utils.JwtUtil;
import com.school.bbs.utils.RedisCache;
import com.school.bbs.utils.request.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * @author lu.xin
 * @version 1.0.0
 * @description 登录接口实现类
 * @createDate 2022/10/2 13:30
 * @since 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录接口
     *
     * @param loginInput User
     * @return LoginOutput out
     */
    @Override
    public LoginOutput login(LoginInput loginInput) {
        // AuthenticationManager authenticationManager进行用户验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInput.getUsrName(), loginInput.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userid生成一个jwt,jwt存入 Result 返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String subject = JSON.toJSONString(loginUser.getUserContext());
        String jwt = JwtUtil.createJWT(subject);
        //把完整的用户信息存入redis,userid作为Key
        redisCache.setCacheObject(AuthConstant.LOGIN + loginUser.getUserContext().getId(), loginUser.getUserContext());
        // 把token响应给前端

        return new LoginOutput(jwt);
    }

    /**
     * 登出接口
     */
    @Override
    public void logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserContext().getId();
        //删除redis中的值
        redisCache.deleteObject(AuthConstant.LOGIN + userId);
    }
}
