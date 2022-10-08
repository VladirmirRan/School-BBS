package com.school.bbs.service.impl;

import com.alibaba.fastjson.JSON;
import com.school.bbs.common.context.LoginContext;
import com.school.bbs.common.context.UserContext;
import com.school.bbs.common.exception.YyghException;
import com.school.bbs.config.UserProperties;
import com.school.bbs.constant.AuthConstant;
import com.school.bbs.controller.input.LoginInput;
import com.school.bbs.controller.output.LoginOutput;
import com.school.bbs.mapper.UserDomainMapper;
import com.school.bbs.service.LoginService;
import com.school.bbs.utils.JwtUtil;
import com.school.bbs.utils.RedisCache;
import com.school.bbs.utils.RsaUtil;
import com.school.bbs.utils.request.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.school.bbs.constant.errorCode.UserCodeEnum.USERNAME_OR_PASSWORD_ERROR;
import static com.school.bbs.constant.errorCode.UserCodeEnum.USER_DISABLE;


/**
 * 登录接口实现类
 *
 * @author lu.xin
 * @version 1.0.0
 * @createDate 2022/10/2 13:30
 * @since 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDomainMapper userDomainMapper;
    @Autowired
    private UserProperties userProperties;

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
        // 解密后的密码
        String decryptPassword;
        // 获取前端传过来的uuid
        String uuid = loginInput.getUuid();
        if (StringUtils.isEmpty(uuid)) {
            throw new YyghException(USERNAME_OR_PASSWORD_ERROR);
        }
        //判断用户是否封禁
        String disAbleUser = redisCache.getCacheObject(AuthConstant.DISABLE_USER + loginInput.getUsrName());
        if (!StringUtils.isEmpty(disAbleUser)) {
            throw new YyghException(USER_DISABLE);
        }
        // 通过uuid从redis缓存中获取密钥对
        LoginContext loginContext = redisCache.getCacheObject(AuthConstant.LOGIN_BEFORE + uuid);
        if (StringUtils.isEmpty(loginContext)) {
            throw new YyghException(USERNAME_OR_PASSWORD_ERROR);
        }
        // 从redis缓存中取出私钥
        String privateKey = loginContext.getPrivateKey();
        // 通过私钥对前端传过来的密码进行解密
        try {
            decryptPassword = RsaUtil.decrypt(loginInput.getPassword(), privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new YyghException(USERNAME_OR_PASSWORD_ERROR);
        }
        // 密码获取到之后，删除缓存
        redisCache.deleteObject(AuthConstant.LOGIN_BEFORE + uuid);
        // AuthenticationManager authenticationManager进行用户验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInput.getUsrName(), decryptPassword);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            Integer wrongTimes = redisCache.getCacheObject(AuthConstant.WRONG_PASSWORD + loginInput.getUsrName());
            //大于允许错误次数则锁定该用户一小时不得访问
            if (wrongTimes++ > userProperties.getAllowedPasswordErrors()) {
                redisCache.setCacheObject(AuthConstant.DISABLE_USER + loginInput.getUsrName(), loginInput.getUsrName(), userProperties.getBlockedTime(), TimeUnit.SECONDS);
            } else {
                //否则次数加一
                redisCache.deleteObject(AuthConstant.WRONG_PASSWORD + loginInput.getUsrName());
                redisCache.setCacheObject(AuthConstant.WRONG_PASSWORD + loginInput.getUsrName(), wrongTimes, userProperties.getBlockedTime(), TimeUnit.SECONDS);
            }
            throw new YyghException(USERNAME_OR_PASSWORD_ERROR);
        }
        //验证通过清空密码错误次数验证
        redisCache.deleteObject(AuthConstant.DISABLE_USER + loginInput.getUsrName());
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
        // lux改20221003：注释备份
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // lux改20221003：用户上下文
        UserContext loginUser = (UserContext) authentication.getPrincipal();
        // lux改20221003：注释备份
//        Long userId = loginUser.getUserContext().getId();
        // lux改20221003：获取 userId
        Long userId = loginUser.getId();
        //删除redis中的值
        redisCache.deleteObject(AuthConstant.LOGIN + userId);
    }
}
