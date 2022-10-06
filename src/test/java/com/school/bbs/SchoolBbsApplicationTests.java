package com.school.bbs;

import com.school.bbs.domain.UserDomain;
import com.school.bbs.mapper.UserDomainMapper;
import com.school.bbs.utils.FormVerifiersUtil;
import com.school.bbs.utils.RsaUtil;
import com.school.bbs.utils.SensitiveReplaceUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;

@SpringBootTest
class SchoolBbsApplicationTests {

    @Autowired
    private UserDomainMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    /**
     * 测试 userMapper
     */
    @Test
    public void testUserMapper() {
        List<UserDomain> userList = userMapper.selectList(null);
        System.out.println("userList = " + userList);
    }

    /**
     * 测试 passwordEncoder
     */
    @Test
    public void testBCryptPasswordEncoder() {
        String encode = passwordEncoder.encode("1234");
        System.out.println("encode = " + encode);
        boolean matches = passwordEncoder.matches("1234", "$2a$10$lTdBD6QOkxXLhs7eHUK0leNmO3wc8uLBvyj8B5O.l8HcIIpL27Xde");
        System.out.println("matches = " + matches);
    }

    /**
     * 测试 Rsa工具类
     *
     * @throws Exception 异常
     */
    @Test
    public void testRsaGenKeyPair() throws Exception {
        long temp = System.currentTimeMillis();
        //生成公钥和私钥和uuid
        Map<Integer, String> genKeyPair = RsaUtil.genKeyPair();
        //加密字符串
        System.out.println("uuid:" + genKeyPair.get(0));
        System.out.println("公钥:" + genKeyPair.get(1));
        System.out.println("私钥:" + genKeyPair.get(2));
        System.out.println("生成密钥消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
        //客户id + 授权时间 + 所用模块
        String message = "123456";
        System.out.println("原文:" + message);
        temp = System.currentTimeMillis();
        //通过原文，和公钥加密。
        String messageEn = RsaUtil.encrypt(message, genKeyPair.get(1));
        System.out.println("密文:" + messageEn);
        System.out.println("加密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
        temp = System.currentTimeMillis();
        //通过密文，和私钥解密。
        String messageDe = RsaUtil.decrypt(messageEn, genKeyPair.get(2));
        System.out.println("解密:" + messageDe);
        System.out.println("解密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
    }

    /**
     * 测试 公钥加密
     *
     * @throws Exception 异常
     */
    @Test
    public void testPublicKeyEncrypt() throws Exception {
        String message = "Crush0321";
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJIelMU2eTbcE4MkapIyEW+XEx6OoVIvJxZeAw40NaorROhi/DE2sMfhYFQ4uW7untwMS67KCq5cQ5AoN3UDqgECAwEAAQ==";
        String messageEn = RsaUtil.encrypt(message, publicKey);
        System.out.println("messageEn = " + messageEn);
    }

    /**
     * 测试 敏感词过滤替换
     */
    @Test
    public void testSensitiveFilter(){
        String target = "卖淫性嫖娼杀人犯恐怖法枪抢共产党色情国民掠夺党法烧死爆炸轮大法";
        System.out.println("target = " + target);
        // SensitiveFilterUtil 暂时不用
//        System.out.println(SensitiveFilterUtil.checkTxt(target));
        // SensitiveReplaceUtil
        String readTxt = SensitiveReplaceUtil.replaceContent(target);
        System.out.println("readTxt = " + readTxt);
    }

    @Test
    public void testPasswordVerifiers(){
        String password = "Crush0321";
        boolean checkPwd = FormVerifiersUtil.checkPwd(password);
        System.out.println("checkPwd = " + checkPwd);
    }

}
