package com.bigdata.hdfs.mapper;


import com.bigdata.hdfs.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mapper的具体表达式
 */
//@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserMapper {

    int save(User user);

    List<User> findAll();

    User findByUsername(String name);

    User findByUsernameAndPassword(String name, String password);

    int update(User user);

    int delete(User user);

    /**
     * 测试MyBatis
     * @param id
     * @return
     */
    User selectUser(int id);
}
