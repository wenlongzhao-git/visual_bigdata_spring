package com.bigdata.hdfs;

import com.bigdata.hdfs.bean.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class HdfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdfsApplication.class, args);
    }

}
