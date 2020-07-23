package com.bigdata.hdfs.service.impl;


import com.bigdata.hdfs.domain.Result;
import com.bigdata.hdfs.domain.User;
import com.bigdata.hdfs.mapper.UserMapper;
import com.bigdata.hdfs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zwl
 * 用户service实现类
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增用户
     * @param user
     * @return
     */
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

    /**
     * 查询所有用户
     * @return
     */
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

    /**
     * 根据用户名查找用户
     * @param map
     * @return
     */
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

    /**
     * 根据用户名和密码查找用户
     * @param map
     * @return
     */
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
            }else {
                result.setMsg("用户不存在！");
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public Result update(User user) {

        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        Map<String,String> map = new HashMap<>();
        map.put("username",user.getUsername());

        User userOld = userMapper.findByUsername(map);

        userOld.setPassword(user.getPassword());
        userOld.setEmail(user.getEmail());
        userOld.setAge(user.getAge());
        userOld.setSex(user.getSex());

        //TODO 后期进行时间的处理
//        userOld.setUpdatetime(new Date());
        try {
            int res = userMapper.update(userOld);
            if(res > 0){
                result.setMsg("修改成功！");
                result.setSuccess(true);
            }else {
                result.setMsg("修改失败！");
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除用户
     * @param map
     * @return
     */
    @Override
    public Result delete(Map map) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        try {
            User user = userMapper.findByUsernameAndPassword(map);
            if(user != null && user.getId() > 0){
                int rel = userMapper.delete(user);
                if(rel > 0){
                    result.setMsg("用户已删除！");
                    result.setSuccess(true);
                } else {
                    result.setMsg("删除失败！");
                }
            }else {
                result.setMsg("删除失败，用户不存在！");
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
