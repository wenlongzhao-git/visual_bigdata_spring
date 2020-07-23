package com.bigdata.hdfs.service.impl;


import com.bigdata.hdfs.domain.Result;
import com.bigdata.hdfs.domain.User;
import com.bigdata.hdfs.mapper.UserMapper;
import com.bigdata.hdfs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result save(User user) {

        user.setUserps("普通");
        user.setIsdel(0);

        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        try {
            int res = userMapper.save(user);
            if(res > 0){
                result.setMsg("创建成功！");
                result.setSuccess(true);
//                user = (User) findByUsername(user.getUsername()).getDetail();
                result.setDetail(user);
            }else {
                result.setMsg("创建失败！");
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public Result findAll() {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        try {
            List<User> user = userMapper.findAll();
            if(user != null && user.size() > 0){
                result.setMsg("用户存在！");
                result.setSuccess(true);
                result.setDetail(user);
            }else {
                result.setMsg("用户不存在！");
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result findByUsername(Map map) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        try {
            User user = userMapper.findByUsername(map);
            if(user != null && user.getId() > 0){
                result.setMsg("用户存在！");
                result.setSuccess(true);
                result.setDetail(user);
            }else {
                result.setMsg("用户不存在！");
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public Result findByUsernameAndPassword(Map map) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        try {
            User user = userMapper.findByUsernameAndPassword(map);
            if(user != null && user.getId() > 0){
                result.setMsg("用户存在！");
                result.setSuccess(true);
                result.setDetail(user);
                User detail = (User) result.getDetail();
            }else {
                result.setMsg("用户不存在！");
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;

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
}
