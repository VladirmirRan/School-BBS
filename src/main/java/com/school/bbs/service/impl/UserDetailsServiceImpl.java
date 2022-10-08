package com.school.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.school.bbs.common.context.UserContext;
import com.school.bbs.common.exception.YyghException;
import com.school.bbs.domain.UserDomain;
import com.school.bbs.mapper.UserDomainMapper;
import com.school.bbs.utils.request.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.school.bbs.constant.BaseStatusEnum.DISABLE;
import static com.school.bbs.constant.DeletedEnum.DELETED;
import static com.school.bbs.constant.errorCode.UserCodeEnum.*;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 实现类
 * @createDate 2022/10/2 12:38
 * @since 1.0.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDomainMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<UserDomain> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDomain::getName, userName);
        UserDomain user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if (Objects.isNull(user)) {
            throw new YyghException(USER_NOT_EXIST);
        } else if (DELETED.getCode() == user.getDeleted()) {
            throw new YyghException(USER_DELETED);
        } else if (DISABLE.getCode() == user.getStatus()) {
            throw new YyghException(USER_DISABLE);
        }
        // TODO:查询对应的权限信息
//        List<String> list = new ArrayList<>(Arrays.asList("test" , "admin"));
//        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        UserContext userContext = toUserContext(user);
        Set<GrantedAuthority> authorities = new HashSet<>(1);
        //把数据封装成UserDetails返回
        return new LoginUser(userContext, user.getName(), user.getPassword(), authorities);
    }

    private UserContext toUserContext(UserDomain user) {
        UserContext userContext = new UserContext();
        userContext.setId(user.getId());
        userContext.setName(user.getName());
        userContext.setRole(user.getRole());
        userContext.setEnable(user.getDeleted());
        userContext.setStatus(user.getStatus());
        userContext.setCreateTime(user.getCreateTime());
        return userContext;
    }

}
