package com.bigdata.hdfs.mapper;


import com.bigdata.hdfs.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mapper的具体表达式
 */
@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
public interface UserMapper {

    User save(User user);

    List<User> findAll();

    List<User> findByUsername(String name);

    List<User> findByUsernameAndPassword(String name, String password);

    User update(User user);

    /**
     * 测试MyBatis
     * @param id
     * @return
     */
    User selectUser(int id);
}
