package com.bigdata.hdfs.mapper;


import com.bigdata.hdfs.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * mapper的具体表达式
 */
//@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserMapper {

    int save(User user);

    List<User> findAll();

    User findByUsername(Map map);

    User findByUsernameAndPassword(Map map);

    int update(User user);

    int delete(User user);

    /**
     * 测试MyBatis
     * @param id
     * @return
     */
    User selectUser(int id);
}
