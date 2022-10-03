package com.school.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.bbs.common.exception.YyghException;
import com.school.bbs.common.result.Result;
import com.school.bbs.common.result.ResultCodeEnum;
import com.school.bbs.modal.domain.User;
import com.school.bbs.mapper.UserMapper;
import com.school.bbs.service.RegisterService;
import com.school.bbs.modal.request.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户注册接口实现类
 * @createDate 2022/10/3 11:25
 * @since 1.0.0
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<UserMapper, User> implements RegisterService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 判断用户名是否存在
     *
     * @param userName String 用户名称
     * @return boolean
     */
    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, userName);
        return count(queryWrapper) > 0;
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
    public Result register(String userName, String password, String checkPassword, String sex, String phone, String avatar) {
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
        if (!StringUtils.hasText(phone)) {
            throw new YyghException(ResultCodeEnum.PHONE_NOT_NULL);
        }
        if (!StringUtils.hasText(sex)) {
            throw new YyghException(ResultCodeEnum.SEX_NOT_NULL);
        }
//        if (!StringUtils.hasText(avatar)) {
//            throw new YyghException(ResultCodeEnum.AVATAR_NOT_NULL);
//        }
        //对数据进行是否存在的判断
        if (userNameExist(userName)) {
            throw new YyghException(ResultCodeEnum.USERNAME_EXIST);
        }
        // TODO:对电话号码的校验
        // 对密码长度进行校验，密码长度不得少于6位
        if (password.length() < 6 || checkPassword.length() < 6) {
            throw new YyghException(ResultCodeEnum.PASSWORD_LENGTH_EIGHT);
        }
        // 对密码进行加密
        String encodePassword = passwordEncoder.encode(password);
        User user = new User();
        user.setName(userName);
        user.setPassword(encodePassword);
        user.setSex(sex);
        user.setPhone(phone);
        user.setAvatar(avatar);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        // TODO:魔法值处理，对应User类的字段类型修改
        user.setCreateUser(100001L);
        user.setRole(3);
        user.setDeleted("0");
        // 存入数据库
        save(user);
        // TODO:响应结果修改
        return new Result(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }
}
