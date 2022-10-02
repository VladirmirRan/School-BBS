package com.school.bbs;

import com.school.bbs.domain.User;
import com.school.bbs.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SchoolBbsApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    /**
     * 测试 userMapper
     */
    @Test
    public void testUserMapper(){
        List<User> userList = userMapper.selectList(null);
        System.out.println("userList = " + userList);
    }

    @Test
    public void testBCryptPasswordEncoder(){
        String encode = passwordEncoder.encode("1234");
        System.out.println("encode = " + encode);
        boolean matches = passwordEncoder.matches("1234", "$2a$10$lTdBD6QOkxXLhs7eHUK0leNmO3wc8uLBvyj8B5O.l8HcIIpL27Xde");
        System.out.println("matches = " + matches);
    }

}
