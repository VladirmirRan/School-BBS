package com.school.bbs.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 密码校验工具类
 * @createDate 2022/10/6 17:04
 * @since 1.0.0
 */
@Component
@Slf4j
public class FormVerifiersUtil {

    /**
     * 数字
     */
    public static final String REG_NUMBER = ".*\\d+.*";

    /**
     * 大写字母
     */
    public static final String REG_UPPERCASE = ".*[A-Z]+.*";

    /**
     * 小写字母
     */
    public static final String REG_LOWERCASE = ".*[a-z]+.*";

    /**
     * 特殊符号
     */
    public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

    /**
     * 密码校验
     * 1.密码长度至少8位以上
     * 2.密码应至少包含大写字母，小写字母，数字，特殊符号中的三项
     * 3.禁止使用相同的数字或字符作为密码，如111111，aaaaaa等
     * 4.禁止使用连续升序或降序的数字或字母作为密码，如123456，abcdef等
     *
     * @param password String 密码
     * @return boolean 校验是否符合规则 true检验合格 false检验不合格
     */
    public static boolean checkPwd(String password) {
        //密码为空或者长度小于8位则返回false
        if (password == null || password.length() < 8) {
            return false;
        }
        int j = 0;
        if (password.matches(REG_NUMBER)) {
            j++;
        }
        if (password.matches(REG_LOWERCASE)) {
            j++;
        }
        if (password.matches(REG_UPPERCASE)) {
            j++;
        }
        if (password.matches(REG_SYMBOL)) {
            j++;
        }
        if (j < 3) {
            return false;
        }
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length - 5; i++) {
            int n1 = chars[i];
            int n2 = chars[i + 1];
            int n3 = chars[i + 2];
            int n4 = chars[i + 3];
            int n5 = chars[i + 4];
            int n6 = chars[i + 5];
            // 判断重复字符
            if (n1 == n2 && n1 == n3 && n1 == n4 && n1 == n5 && n1 == n6) {
                return false;
            }
            // 判断连续字符： 正序 + 倒序
            if ((n1 + 1 == n2 && n1 + 2 == n3 && n1 + 3 == n4 && n1 + 4 == n5 && n1 + 5 == n6) || (n1 - 1 == n2 && n1 - 2 == n3 && n1 - 3 == n4 && n1 - 4 == n5 && n1 - 5 == n6)) {
                return false;
            }
        }
        return true;
    }
}
