package com.bigdata.hdfs.service;

import com.bigdata.hdfs.dao.LoginDao;
import com.bigdata.hdfs.domain.LoginUser;
import com.bigdata.hdfs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huangds on 2017/10/28.
 */
@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    public List<LoginUser> userListAll(){
        List<LoginUser> userList = loginDao.findAll();
        return userList;
    }

    public boolean verifyLogin(LoginUser user){
        List<LoginUser> userList = loginDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userList.size()>0;
    }

    public boolean isExist(LoginUser user){
        List<LoginUser> userList = loginDao.findByUsername(user.getUsername());
        if(user != null && userList.isEmpty() && userList.size() == 0) {
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public Boolean save(LoginUser user){
        if(isExist(user)) {
            loginDao.save(user);
            return true;
        }else {
            // TODO
            System.out.println("添加失败：");
            return false;
        }
    }

}
