package com.herong.demo.service;

import com.herong.demo.domain.User;
import com.herong.demo.domain.query.UserQuery;

import java.util.List;

public interface UserService {
    User getUserById(Long userId);
    User getUserByUserAccount(String uerAccount);
    void insert(User user);
    void update(User user);
    void delete(Long id);
    void querByPage(UserQuery query);
    List<User> queryUserList(UserQuery query);
}
