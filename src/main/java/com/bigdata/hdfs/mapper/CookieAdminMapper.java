package com.bigdata.hdfs.mapper;


import com.bigdata.hdfs.domain.CookieAdmin;
import com.bigdata.hdfs.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author zwl
 * 用户Cookie-token管理mapper类
 */
//@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface CookieAdminMapper {

    /**
     * 新增
     * @param cookieAdmin
     * @return
     */
    int save(CookieAdmin cookieAdmin);

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
    int update(CookieAdmin cookieAdmin);

    /**
     * 删除
     * @param cookieAdmin
     * @return
     */
    int delete(CookieAdmin cookieAdmin);
}
