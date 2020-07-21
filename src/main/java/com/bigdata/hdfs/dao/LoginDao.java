package com.bigdata.hdfs.dao;

import com.bigdata.hdfs.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 用户Dao类
 */
@Repository
public interface LoginDao extends CrudRepository<User, Long> {

  public User save(User user);

  public List<User> findAll();

  public List<User> findByUsername(String name);

  public List<User> findByUsernameAndPassword(String name, String password);
}
