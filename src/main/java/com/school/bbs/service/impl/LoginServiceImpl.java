package com.school.bbs.service.impl;

import com.school.bbs.common.result.Result;
import com.school.bbs.common.result.ResultCodeEnum;
import com.school.bbs.domain.LoginUser;
import com.school.bbs.domain.User;
import com.school.bbs.service.LoginService;
import com.school.bbs.utils.JwtUtil;
import com.school.bbs.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
     * @param user User
     * @return Result
     */
    @Override
    public Result login(User user) {
        // AuthenticationManager authenticationManager进行用户验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getName() , user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userid生成一个jwt,jwt存入 Result 返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把完整的用户信息存入redis,userid作为Key
        redisCache.setCacheObject("login:" + userId , loginUser);
        // 把token响应给前端
        HashMap<String , String> map = new HashMap<>();
        map.put("token" , jwt);
        return new Result(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), map);
    }
}
