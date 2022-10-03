package com.school.bbs.utils.request;

import com.school.bbs.common.context.UserContext;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户登录实体
 * @createDate 2022/10/2 12:44
 * @since 1.0.0
 */
@Getter
public class LoginUser extends User {
    /**
     * 用户上下文
     */
    private UserContext userContext;


    public LoginUser(UserContext userContext, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userContext = userContext;
    }

    public LoginUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


}
