package com.herong.demo.service.impl;

import com.herong.demo.dao.UserDao;
import com.herong.demo.domain.User;
import com.herong.demo.domain.query.UserQuery;
import com.herong.demo.exception.ServiceCodeException;
import com.herong.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User getUserByUserAccount(String uerAccount) {
        return userDao.getUserByUserAccount(uerAccount);
    }

    @Override
    public void insert(User user) {
        User userInfo = userDao.getUserByUserAccount(user.getUserCode());
        if (userInfo != null){
            throw new ServiceCodeException(2001,"账号已注册");
        }
        user.setUserAccount(user.getUserCode());
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        User userInfo = userDao.getUserByUserAccount(user.getUserCode());
        if (userInfo == null){
            throw new ServiceCodeException(2001,"用户不存在");
        }
        user.setId(userInfo.getId());
        user.setUserType(null);
        user.setUserAccount(null);
        user.setUserCode(null);
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteByPrimaryKey(id);
    }

    @Override
    public void querByPage(UserQuery query) {
        query.startPage(true);
        List<User> users = userDao.queryByPage(query);
        query.compable(users);
    }

    @Override
    public List<User> queryUserList(UserQuery query) {
        query.startPage(false);
        List<User> users = userDao.queryByPage(query);
        return users;
    }
}

