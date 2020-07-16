package com.bigdata.hdfs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zwl
 * 首页-跳转到登录页面
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "login2";
    }
}
