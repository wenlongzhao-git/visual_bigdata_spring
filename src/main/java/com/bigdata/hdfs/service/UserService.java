package com.bigdata.hdfs.service;

import com.bigdata.hdfs.domain.User;

import java.util.List;

public interface UserService {

    boolean save(User user);

    List<User> findAll();

    User findByUsername(String name);

    User findByUsernameAndPassword(String name, String password);

    boolean update(User user);

    boolean delete(User user);

    /**
     * 测试MyBatis
     * @param id
     * @return
     */
    User selectUser(int id);
}
