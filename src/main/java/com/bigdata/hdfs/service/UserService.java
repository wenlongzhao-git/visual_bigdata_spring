package com.bigdata.hdfs.service;

import com.bigdata.hdfs.domain.Result;
import com.bigdata.hdfs.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Result save(User user);

    Result findAll();

    Result findByUsername(Map map);

    Result findByUsernameAndPassword(Map map);

    boolean update(User user);

    boolean delete(User user);
}
