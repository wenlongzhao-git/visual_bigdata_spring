package com.bigdata.hdfs.service;

import com.bigdata.hdfs.domain.Result;
import com.bigdata.hdfs.domain.User;

import java.util.Map;

/**
 * @author zwl
 * 用户service类
 */
public interface UserService {

    /**
     * 新增用户
     * @param user
     * @return
     */
    Result save(User user);

    /**
     * 查询所有用户
     * @return
     */
    Result findAll();

    /**
     * 根据用户名查找用户
     * @param map
     * @return
     */
    Result findByUsername(Map map);

    /**
     * 根据用户名和密码查找用户
     * @param map
     * @return
     */
    Result findByUsernameAndPassword(Map map);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Result update(User user);

    /**
     * 删除用户
     * @param map
     * @return
     */
    Result delete(Map map);
}
