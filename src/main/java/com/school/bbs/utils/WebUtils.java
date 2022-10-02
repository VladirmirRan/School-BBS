package com.school.bbs.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description Web工具类
 * @createDate 2022/10/2 11:40
 * @since 1.0.0
 */
public class WebUtils {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
