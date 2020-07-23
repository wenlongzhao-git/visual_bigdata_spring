package com.bigdata.hdfs.mapper;


import com.bigdata.hdfs.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zwl
 * 用户管理mapper类
 */
//@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserMapper {

    /**
     * 新增用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户名查找用户
     * @param map
     * @return
     */
    User findByUsername(Map map);

    /**
     * 根据用户名和密码查找用户
     * @param map
     * @return
     */
    User findByUsernameAndPassword(Map map);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    int delete(User user);
}
