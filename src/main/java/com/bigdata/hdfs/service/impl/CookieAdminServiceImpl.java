package com.bigdata.hdfs.service.impl;


import com.bigdata.hdfs.domain.CookieAdmin;
import com.bigdata.hdfs.mapper.CookieAdminMapper;
import com.bigdata.hdfs.service.CookieAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zwl
 * service实现类
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class CookieAdminServiceImpl implements CookieAdminService {

    @Autowired
    private CookieAdminMapper cookieAdminMapper;

    /**
     * 新增
     * @param cookieAdmin
     * @return
     */
    @Override
    public Boolean save(CookieAdmin cookieAdmin) {
        cookieAdmin.setCreatetime(new Date(System.currentTimeMillis()));

        try {
            int res = cookieAdminMapper.save(cookieAdmin);
            if(res > 0){
                return  true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据用户名查找
     * @param map
     * @return
     */
    @Override
    public CookieAdmin findByUsername(Map map) {
        try {
            CookieAdmin cookieAdmin = cookieAdminMapper.findByUsername(map);
            if(cookieAdmin != null && cookieAdmin.getId() > 0){
                return cookieAdmin;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 更新信息
     * @param cookieAdmin
     * @return
     */
    @Override
    public Boolean update(CookieAdmin cookieAdmin) {

        Map<String,String> map = new HashMap<>();
        map.put("username",cookieAdmin.getUsername());

        CookieAdmin cookieAdminOld = cookieAdminMapper.findByUsername(map);

        cookieAdminOld.setToken(cookieAdmin.getToken());

        //TODO 后期进行时间的处理
        cookieAdminOld.setUpdatetime(new Date());
        try {
            int res = cookieAdminMapper.update(cookieAdminOld);
            if(res > 0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除
     * @param map
     * @return
     */
    @Override
    public Boolean delete(Map map) {

        try {
            CookieAdmin cookieAdmin = cookieAdminMapper.findByUsername(map);
            if(cookieAdmin != null && cookieAdmin.getId() > 0){
                int rel = cookieAdminMapper.delete(cookieAdmin);
                if(rel > 0){
                    return true;
                }
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
