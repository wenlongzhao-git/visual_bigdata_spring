package com.bigdata.hdfs.controller;

import com.bigdata.hdfs.config.WebSecurityConfig;
import com.bigdata.hdfs.bean.User;
import com.bigdata.hdfs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by huangds on 2017/10/24.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account, Model model){

        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginVerify")
    public String loginVerify(String username,String password,HttpSession session){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean verify = loginService.verifyLogin(user);
        if (verify) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            System.out.println(username);
            return "redirect:/index";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("+remoce+qian:"+session.getAttribute("username"));
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        System.out.println(session.getId()+"+remoce:"+session.getAttributeNames());
        System.out.println("+remoce+hou:"+session.getAttribute("username"));
        return "redirect:/login";
    }

    /**
     * 注册
     *
     * @return
     */
    @PostMapping("/adduser")
    public String adduser(String userid,String username,String password,String userps,HttpSession session){
        User user = new User();
        user.setUserid(userid);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserps(userps);

        loginService.save(user);
        return "redirect:/login";
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
