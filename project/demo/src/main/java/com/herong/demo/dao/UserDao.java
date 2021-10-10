package com.herong.demo.dao;

import com.herong.demo.domain.User;
import com.herong.demo.domain.query.UserQuery;

import java.util.List;

/**
 * @author herong22384
 * @date 2020/3/22 13:59
 */
public interface UserDao {
    User selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
    void insert(User user);
    void updateByPrimaryKeySelective(User user);
    List<User> queryByPage(UserQuery query);
    User getUserByUserAccount(String userAccount);
}
