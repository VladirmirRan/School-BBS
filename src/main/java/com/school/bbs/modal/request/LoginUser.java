package com.school.bbs.modal.request;

import com.school.bbs.modal.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户登录实体
 * @createDate 2022/10/2 12:44
 * @since 1.0.0
 */
public class LoginUser implements UserDetails {

    /**
     * 用户实体类
     */
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginUser() {
    }

    public LoginUser(User user) {
        this.user = user;
    }

    /**
     * 获取权限信息
     *
     * @return Collection null
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取密码
     *
     * @return String null
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取用户名
     *
     * @return String null
     */
    @Override
    public String getUsername() {
        return user.getName();
    }

    /**
     * 是否没过期
     *
     * @return boolean true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否没锁定
     *
     * @return boolean true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 是否没有超时
     *
     * @return boolean true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     *
     * @return boolean true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
