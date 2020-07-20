package com.bigdata.hdfs.config;

/**
 * Created by huangds on 2017/10/24.
 */

import com.bigdata.hdfs.utils.CookieUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录配置
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter{

    @Bean
    public SecurityInterceptor getSecurityInterceptor(){
        return new SecurityInterceptor();
    }

    @Override
    public void  addInterceptors( InterceptorRegistry registry){
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/regist**");
        addInterceptor.excludePathPatterns("/errors**");
        addInterceptor.excludePathPatterns("/");

        addInterceptor.addPathPatterns("/**");
        addInterceptor.addPathPatterns("/index**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter{
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
            HttpSession session = request.getSession();

            if(CookieUtils.getCookie(request,"token") != null && CookieUtils.getCookie(request,"token").equals(CookieUtils.TOKENX)){
                CookieUtils.writeCookie(response, "code", CookieUtils.SUCCESS);
                return true;
            }

//            跳转到登录页
            String url = "/errors";
            CookieUtils.writeCookie(response, "code", CookieUtils.ERROR_NOTLOGIN);
            response.sendRedirect(url);
            return false;
        }
    }
}
