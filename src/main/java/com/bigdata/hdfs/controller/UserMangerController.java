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
 */
@Controller
public class UserMangerController {

    @Autowired
    private LoginService loginService;


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

    /*@PostMapping("/loginVerify")
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
                CookieUtils.writeCookie(response, "token", CookieUtils.TOKENX);
                System.out.println("登陆成功，并添加新token, token = " + CookieUtils.TOKENX);
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
    }*/


    /**
     * 登出实现，进行token及username的过期
     * @param response
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    public Map<String, String> logout(HttpServletResponse response){
        CookieUtils.writeCookie(response, "token", null,0);
        CookieUtils.writeCookie(response, "username", null,0);

        Map<String,String> map = new HashMap<>();
        map.put("islogin","false");
        return map;
    }

    /*@ResponseBody
    @GetMapping("/findAll")
    public List<User> findAll(){
        List<User> userList = loginService.userListAll();
        return userList;
    }*/

    /**
     * 验证一个用户是否存在
     * @param username
     * @return
     */
    @PostMapping("/verifyExist")
    @ResponseBody
    public Result verifyExist(String username){

        Result result = userService.findByUsername(username);
        return result;
    }

    /**
     * 注册-新增一个用户信息
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/adduser")
    @ResponseBody
    public Result adduser(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserps("普通");
//        user.setCreatetime(System.tim);

        Result result = userService.save(user);

        return result;
    }

    @GetMapping("/myBatis")
    @ResponseBody
    public User getUserInfo(int id){
        return userService.selectUser(id);
    }
}
