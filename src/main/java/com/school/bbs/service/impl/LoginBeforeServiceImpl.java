package com.school.bbs.service.impl;

import com.school.bbs.common.context.ContextHolder;
import com.school.bbs.common.context.LoginContext;
import com.school.bbs.constant.AuthConstant;
import com.school.bbs.controller.output.LoginBeforeOutput;
import com.school.bbs.service.LoginBeforeService;
import com.school.bbs.utils.RedisCache;
import com.school.bbs.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户登录前接口实现类
 * @createDate 2022/10/5 12:16
 * @since 1.0.0
 */
@Service
public class LoginBeforeServiceImpl implements LoginBeforeService {

    @Autowired
    private RedisCache redisCache;

    /**
     * 用户登陆前生成密钥对
     *
     * @return LoginBeforeOutput 出参
     */
    @Override
    public LoginBeforeOutput loginBefore() {
        Map<Integer, String> genKeyPair = null;
        try{
            // 生成公钥和私钥和uuid
            genKeyPair = RsaUtil.genKeyPair();
        }catch (Exception e){
            e.printStackTrace();
        }
        // uuid
        String uuid = genKeyPair.get(0);
        // 公钥
        String publicKey = genKeyPair.get(1);
        // 私钥
        String privateKey = genKeyPair.get(2);
        Map<String, String> genKey = new HashMap<>();
        genKey.put("uuid", uuid);
        genKey.put("publicKey", publicKey);
        genKey.put("privateKey", privateKey);
        LoginContext loginContext = new LoginContext();
        loginContext.setUuid(uuid);
        loginContext.setPublicKey(publicKey);
        loginContext.setPrivateKey(privateKey);
        //把uuid、公钥、私钥存入redis,uuid作为Key
        redisCache.setCacheObject(AuthConstant.LOGINBEFORE + loginContext.getUuid(), loginContext);
        LoginBeforeOutput loginBeforeOutput = new LoginBeforeOutput();
        loginBeforeOutput.setUuid(uuid);
        loginBeforeOutput.setPublicKey(publicKey);
        return loginBeforeOutput;
    }
}
