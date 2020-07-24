package com.bigdata.hdfs.config;

/**
 * Created by huangds on 2017/10/24.
 */

import com.bigdata.hdfs.domain.CookieAdmin;
import com.bigdata.hdfs.service.CookieAdminService;
import com.bigdata.hdfs.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录配置
 * @author zwl
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

        @Autowired
        private CookieAdminService cookieAdminService;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {

            String token = CookieUtils.getCookie(request,CookieUtils.TOKEN);
            String username = CookieUtils.getCookie(request,"username");
            Map<String,String> map = new HashMap<>(16);
            map.put("username",username);

            CookieAdmin cookieAdmin = cookieAdminService.findByUsername(map);

            if(token != null && token.equals(cookieAdmin.getToken())){
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
