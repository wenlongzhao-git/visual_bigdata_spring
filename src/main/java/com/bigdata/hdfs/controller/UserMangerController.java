package com.bigdata.hdfs.controller;

import com.bigdata.hdfs.config.WebSecurityConfig;
import com.bigdata.hdfs.domain.Result;
import com.bigdata.hdfs.domain.User;
import com.bigdata.hdfs.service.LoginService;
import com.bigdata.hdfs.service.UserService;
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
 * 用户管理类
 * @author zwl
 */
@Controller
public class UserMangerController {


    @Autowired
    private UserService userService;

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
    public Result loginVerify(@RequestParam Map<String,Object> map, HttpServletRequest request, HttpServletResponse response,
                                           @CookieValue(value = "token", required = false) String token){

        Result result = userService.findByUsernameAndPassword(map);

        if (result.isSuccess()) {
            if (token == null || CookieUtils.TOKENX != CookieUtils.getCookie(request,"token")) {
                CookieUtils.writeCookie(response, "token", CookieUtils.TOKENX);
                System.out.println("登陆成功，并添加新token, token = " + CookieUtils.TOKENX);
            } else {
                //TODO 后期token值做随机值入库后，这里要先进行库里面的token查询，并重置到期时间
            }
            CookieUtils.writeCookie(response, "code", CookieUtils.SUCCESS);
            CookieUtils.writeCookie(response, "username", ((User)result.getDetail()).getUsername());
        } else {
        }
        return result;
    }

    @ResponseBody
    @GetMapping("/findAll")
    public Result findAll(){
        return userService.findAll();
    }

    /**
     * 验证一个用户是否存在
     * @param map
     * @return
     */
    @PostMapping("/verifyExist")
    @ResponseBody
    public Result verifyExist(@RequestParam Map<String,Object> map){
        return userService.findByUsername(map);
    }

    /**
     * 注册-新增一个用户信息
     * @param user
     * @return
     */
    @PostMapping("/adduser")
    @ResponseBody
    public Result adduser(@RequestBody User user){
        System.out.println(user);
        return userService.save(user);
    }


    /**
     * 登出实现，进行token及username的过期
     * @param response
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletResponse response){
        CookieUtils.writeCookie(response, "token", null,0);
        CookieUtils.writeCookie(response, "username", null,0);

        Result result = new Result();
        result.setSuccess(true);
        result.setMsg("退出成功！");
        result.setDetail(null);

        return result;
    }
}
