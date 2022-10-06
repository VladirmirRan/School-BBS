package com.school.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.bbs.common.context.LoginContext;
import com.school.bbs.common.exception.YyghException;
import com.school.bbs.constant.AuthConstant;
import com.school.bbs.constant.ResultCodeEnum;
import com.school.bbs.domain.UserDomain;
import com.school.bbs.mapper.UserDomainMapper;
import com.school.bbs.service.RegisterService;
import com.school.bbs.utils.FormVerifiersUtil;
import com.school.bbs.utils.RedisCache;
import com.school.bbs.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户注册接口实现类
 * @createDate 2022/10/3 11:25
 * @since 1.0.0
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<UserDomainMapper, UserDomain> implements RegisterService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisCache redisCache;

    /**
     * 判断用户名是否存在
     *
     * @param userName String 用户名称
     * @return boolean
     */
    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<UserDomain> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDomain::getName, userName);
        return this.count(queryWrapper) > 0;
    }

    /**
     * 用户注册接口
     *
     * @param userName      String 用户名称
     * @param password      String 用户密码
     * @param checkPassword String 用户确认密码
     * @param sex           String 用户性别
     * @param phone         String 用户电话
     * @param avatar        String 用户头像
     * @return Result 响应结果
     */
    @Override
    public void register(String userName, String password, String checkPassword, Integer sex, String phone, String avatar, String uuid) {
        // 对数据进行非空判断
        if (!StringUtils.hasText(userName)) {
            throw new YyghException(ResultCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(password)) {
            throw new YyghException(ResultCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(checkPassword)) {
            throw new YyghException(ResultCodeEnum.PASSWORD_NOT_NULL);
        }
//        if (!StringUtils.hasText(phone)) {
//            throw new YyghException(ResultCodeEnum.PHONE_NOT_NULL);
//        }
//        if (!StringUtils.hasText(sex.toString())) {
//            throw new YyghException(ResultCodeEnum.SEX_NOT_NULL);
//        }
//        if (!StringUtils.hasText(avatar)) {
//            throw new YyghException(ResultCodeEnum.AVATAR_NOT_NULL);
//        }
        if (StringUtils.isEmpty(uuid)) {
            throw new YyghException(ResultCodeEnum.UUID_NOT_NULL);
        }
        // 解密后的密码
        String decryptPassword = null;
        // 解密后的检查密码
        String decryptCheckPassword = null;
        // 通过uuid从redis缓存中获取密钥对
        LoginContext loginContext = redisCache.getCacheObject(AuthConstant.LOGIN_BEFORE + uuid);
        // 从redis缓存中取出私钥
        String privateKey = loginContext.getPrivateKey();
        // 通过私钥对前端传过来的密码进行解密
        try {
            decryptPassword = RsaUtil.decrypt(password, privateKey);
            decryptCheckPassword = RsaUtil.decrypt(checkPassword, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 密码获取到之后，删除缓存
        redisCache.deleteObject(AuthConstant.LOGIN_BEFORE + uuid);
        //对数据进行是否存在的判断
        if (userNameExist(userName)) {
            throw new YyghException(ResultCodeEnum.USERNAME_EXIST);
        }
        // 对两次输入的密码进行校验
        assert decryptPassword != null;
        if (!decryptPassword.equals(decryptCheckPassword)){
            throw new YyghException(ResultCodeEnum.PASSWORD_NOT_MATCH);
        }
        // 对密码长度进行校验，密码长度不得少于8位
//        if (password.length() < AuthConstant.PASSWORD_LENGTH_EIGHT || checkPassword.length() < AuthConstant.PASSWORD_LENGTH_EIGHT) {
//            throw new YyghException(ResultCodeEnum.PASSWORD_LENGTH_EIGHT);
//        }
        // 对密码进行校验
        if (!FormVerifiersUtil.checkPwd(decryptPassword)){
            throw new YyghException(ResultCodeEnum.PASSWORD_LENGTH_EIGHT);
        }
        // 对密码进行加密
        String encodePassword = passwordEncoder.encode(decryptPassword);
        UserDomain user = new UserDomain();
        user.setName(userName);
        user.setPassword(encodePassword);
        user.setSex(sex);
        user.setPhone(phone);
        user.setAvatar(avatar);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setCreateUser(AuthConstant.ADMIN_ID);
        user.setRole(AuthConstant.USER_ROLE_THREE);
        user.setDeleted(AuthConstant.IS_DELETED_FALSE);
        // 存入数据库
        save(user);
    }
}
