package com.bigdata.hdfs.controller;

import com.bigdata.hdfs.bean.ConfigBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "hdfs接口")
@RequestMapping("/hdfs")
public class ConfigController {


    /*@Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    @RequestMapping("/")
    public String hexo(){
        return username + "," + password;
    }*/


    @Autowired
    ConfigBean configBean;

//    @RequestMapping("/hexo")
    @RequestMapping(value = "/hexo",method = RequestMethod.POST)
    @ApiOperation("hexo接口")
    public String hexo2(){
        return configBean.getUsername() + configBean.getUsername();
    }

}
