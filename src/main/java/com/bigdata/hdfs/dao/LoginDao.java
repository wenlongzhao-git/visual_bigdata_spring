package com.bigdata.hdfs.dao;

import com.bigdata.hdfs.domain.LoginUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 用户Dao类
 */
@Repository
public interface LoginDao extends CrudRepository<LoginUser, Long> {

  public LoginUser save(LoginUser user);

  public List<LoginUser> findAll();

  public List<LoginUser> findByUsername(String name);

  public List<LoginUser> findByUsernameAndPassword(String name, String password);
}
