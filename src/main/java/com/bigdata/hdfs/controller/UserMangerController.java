package com.bigdata.hdfs.controller;

import com.bigdata.hdfs.domain.CookieAdmin;
import com.bigdata.hdfs.domain.Result;
import com.bigdata.hdfs.domain.User;
import com.bigdata.hdfs.service.CookieAdminService;
import com.bigdata.hdfs.service.UserService;
import com.bigdata.hdfs.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理类
 * @author zwl
 */
@Controller
public class UserMangerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    
    @Autowired
    private CookieAdminService cookieAdminService;

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
    public Result loginVerify(@RequestParam Map<String,Object> map, HttpServletResponse response,
                              @CookieValue(value = "token", required = false) String token,
                              @CookieValue(value = "username", required = false) String username){

        long start = System.currentTimeMillis();
        logger.info("start login Verify");
        Result result = userService.findByUsernameAndPassword(map);

        try {
            if (result.isSuccess()) {
                CookieAdmin cookieAdmin = cookieAdminService.findByUsername(map);

                if (token != null && username != null && cookieAdmin != null && username.equals(cookieAdmin.getUsername()) && token.equals(cookieAdmin.getToken())) {
                    CookieUtils.writeCookie(response, CookieUtils.TOKEN, cookieAdmin.getToken());
                    CookieUtils.writeCookie(response, "username", username);
                    logger.info("登陆成功，并取出Cookie, token = " + cookieAdmin.getToken()+",username = " + (String) map.get("username"));
                } else {
                    cookieAdminService.delete(map);
                    cookieAdmin = new CookieAdmin();
                    token = CookieUtils.createCookieValue();
                    cookieAdmin.setUsername((String) map.get("username"));
                    cookieAdmin.setToken(token);
                    Boolean save = cookieAdminService.save(cookieAdmin);
                    if(save){
                        CookieUtils.writeCookie(response, CookieUtils.TOKEN, cookieAdmin.getToken());
                        CookieUtils.writeCookie(response, "username", (String) map.get("username"));
                        logger.info("登陆成功，并生成添加新Cookie, token = " + cookieAdmin.getToken()+",username = " + (String) map.get("username"));
                    } else {
                        CookieUtils.writeCookie(response, CookieUtils.TOKEN, null);
                        CookieUtils.writeCookie(response, "username", null);
                        logger.info("登陆失败，并添加新Cookie未成功, token = " + cookieAdmin.getToken()+",username = " + (String) map.get("username"));
                        result.setSuccess(false);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("======used [" + (System.currentTimeMillis() - start) / 1000 + "] seconds ..");
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
        return userService.save(user);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody User user){
        return userService.update(user);
    }

    /**
     * 登出实现，进行token及username的过期
     * @param response
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletResponse response,@CookieValue(value = "username", required = false) String username){

        Map<String,String > map = new HashMap<>();
        map.put("username",username);

        if(username != null){
            cookieAdminService.delete(map);
        }

        CookieUtils.writeCookie(response, "token", null,0);
        CookieUtils.writeCookie(response, "username", null,0);

        Result result = new Result();
        result.setSuccess(true);
        result.setMsg("退出成功！");
        result.setDetail(null);

        return result;
    }

    /**
     * 删除用户----》》进行字段更新来达到删除效果
     * @param map
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam Map<String,Object> map){
        return userService.delete(map);
    }
}
