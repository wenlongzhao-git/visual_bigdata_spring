package com.bigdata.hdfs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author zwl
 * @create: ---
 */
@RestController
@CrossOrigin(origins = "*")
@Api(tags = "hdfs接口")
@RequestMapping("/hdfs")
public class HelloController {
    @ApiOperation("hello接口")
    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Boot";
    }
}
