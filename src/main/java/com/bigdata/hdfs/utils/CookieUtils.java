package com.bigdata.hdfs.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Cookie Utils 类
 * 进行Cookie 的获取、新增、重置操作
 */
public class CookieUtils {

    // TODO 后期做成动态获取，存储到数据库中，每次验证时到数据库中取并验证内容和时间
    public static final String TOKENX = "1234";
    public static final String ERROR_NOTLOGIN = "603";
    public static final String SUCCESS = "200";


    /**
     * 根据Cookie名获取Cookie值
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 输出所有Cookie列表
     * @param request
     * @return
     */
    public static Map<String,String> showCookie(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        Map<String,String> mapCookie = new HashMap<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                mapCookie.put(cookie.getName(),cookie.getValue());
            }
        }
        return mapCookie;
    }


    /**
     * 向Cookie中新增一个Cookie
     * @param response
     * @param cookieName
     * @param value
     */
    public static void writeCookie(HttpServletResponse response, String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(5 * 60);
        response.addCookie(cookie);
    }


    /**
     * 设置Cookie的某个key的过期时间
     * 可以重置也可以设置为已过期即Cookie清除
     * @param response
     * @param cookieName
     * @param value
     * @param time
     */
    public static void writeCookie(HttpServletResponse response, String cookieName, String value,int time) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(time);
        response.addCookie(cookie);
    }
}