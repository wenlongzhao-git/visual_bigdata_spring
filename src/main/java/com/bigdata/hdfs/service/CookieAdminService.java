package com.bigdata.hdfs.service;

import com.bigdata.hdfs.domain.CookieAdmin;
import com.bigdata.hdfs.domain.Result;

import java.util.Map;

/**
 * @author zwl
 * service类
 */
public interface CookieAdminService {

    /**
     * 新增
     * @param cookieAdmin
     * @return
     */
    Boolean save(CookieAdmin cookieAdmin);

    /**
     * 根据用户名查找
     * @param map
     * @return
     */
    CookieAdmin findByUsername(Map map);

    /**
     * 更新信息
     * @param cookieAdmin
     * @return
     */
    Boolean update(CookieAdmin cookieAdmin);

    /**
     * 删除
     * @param map
     * @return
     */
    Boolean delete(Map map);
}
