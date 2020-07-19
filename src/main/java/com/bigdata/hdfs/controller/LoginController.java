package com.bigdata.hdfs.controller;

import com.bigdata.hdfs.config.WebSecurityConfig;
import com.bigdata.hdfs.domain.Result;
import com.bigdata.hdfs.domain.User;
import com.bigdata.hdfs.service.LoginService;
import com.bigdata.hdfs.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangds on 2017/10/24.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String index(Model model){

        return "index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/errors")
    @ResponseBody
    public Map<String, String> errors(){
        Map<String,String> map = new HashMap<>();
        map.put("islogin","false");
        return map;
    }

    @PostMapping("/loginVerify")
    @ResponseBody
    public Map<String, String> loginVerify(String username, String password, HttpServletRequest request, HttpServletResponse response,
                                           @CookieValue(value = "token", required = false) String token){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean verify = loginService.verifyLogin(user);

        Map<String,String> map = new HashMap<>();
        map.put("username",user.getUsername());

        if (verify) {
            if (token == null || CookieUtils.TOKENX != CookieUtils.getCookie(request,"token")) {
                System.out.println("登陆成功，并添加新token");
                CookieUtils.writeCookie(response, "token", CookieUtils.TOKENX);
            } else {
                //TODO 后期token值做随机值入库后，这里要先进行库里面的token查询，并重置到期时间
            }
            CookieUtils.writeCookie(response, "code", CookieUtils.SUCCESS);
            CookieUtils.writeCookie(response, "username", username);
            map.put("islogin","true");
        } else {
            map.put("islogin","false");
        }
        return map;
    }

    @PostMapping("/logout")
    @ResponseBody
    public Map<String, String> logout(HttpServletRequest request, HttpServletResponse response){
        CookieUtils.writeCookie(response, "token", null,0);
        CookieUtils.writeCookie(response, "username", null,0);

        Map<String,String> map = new HashMap<>();
        map.put("islogin","false");
        return map;
    }

    /**
     * 注册
     *
     * @return
     */
    @PostMapping("/adduser")
    @ResponseBody
    public Map<String, String> adduser(String username,String password,HttpSession session){
        User user = new User();
        user.setUserid(username);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserps("普通");

        Boolean isAdd = loginService.save(user);

        Map<String,String> map = new HashMap<>();
        map.put("username",user.getUsername());

        if (isAdd) {
            map.put("isadd","true");
            return map;
        } else {
            map.put("isadd","false");
            return map;
        }
    }

    @GetMapping("/regist")
    public String regist(){
        return "regist";
    }

    @ResponseBody
    @GetMapping("/findAll")
    public List<User> findAll(){
        List<User> userList = loginService.userListAll();
        return userList;
    }

}
