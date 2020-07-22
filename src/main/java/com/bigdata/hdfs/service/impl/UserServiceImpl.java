package com.bigdata.hdfs.service.impl;


import com.bigdata.hdfs.domain.User;
import com.bigdata.hdfs.mapper.UserMapper;
import com.bigdata.hdfs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    /*public Result regist(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User existUser = userMapper.findUserByName(user.getUsername());
            if(existUser != null){
                //如果用户名已存在
                result.setMsg("用户名已存在");

            }else{
                userMapper.regist(user);
                //System.out.println(user.getId());
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    *//**
     * 登录
     * @param user 用户名和密码
     * @return Result
     *//*
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Long userId= userMapper.login(user);
            if(userId == null){
                result.setMsg("用户名或密码错误");
            }else{
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(userId);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }*/


    @Override
    public boolean save(User user) {
        int result = userMapper.save(user);
        if(result > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findByUsername(String name) {
        return userMapper.findByUsername(name);
    }

    @Override
    public User findByUsernameAndPassword(String name, String password) {
        return userMapper.findByUsernameAndPassword(name,password);
    }

    @Override
    public boolean update(User user) {
        int result = userMapper.update(user);
        if(result > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        int result = userMapper.delete(user);
        if(result > 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 测试MyBatis
     * @param id
     * @return
     */
    @Override
    public User selectUser(int id) {
        return userMapper.selectUser(id);
    }
}
