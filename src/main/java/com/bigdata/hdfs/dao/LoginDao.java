package com.bigdata.hdfs.dao;

import com.bigdata.hdfs.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by huangds on 2017/10/28.
 */
@Repository
public interface LoginDao extends CrudRepository<User, Long> {

  public List<User> findByUsernameAndPassword(String name, String password);

  public User save(User user);

  public List<User> findByUsername(String name);

  public List<User> findAll();
}
