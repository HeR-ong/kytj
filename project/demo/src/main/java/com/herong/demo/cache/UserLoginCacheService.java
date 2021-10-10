package com.herong.demo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class UserLoginCacheService {

    private static final String USER_LOGIN_CACHE_KEY = "user:login:cache:{0}";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 将用户微信授权登录的信息放入缓存
     * @param userId
     * @param value
     *
     * */
    public void addUserLoginCache(Long userId,String value){
        String cacheKey = MessageFormat.format(USER_LOGIN_CACHE_KEY,userId);
        if (!redisTemplate.hasKey(cacheKey)){
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(cacheKey,value,24*60*60*1000);
        }
    }

    /**
     * 判断用户当前是否登录
     * @param userId
     *
     * */
    public Boolean isUserLoginByWx(Long userId){
        String cacheKey = MessageFormat.format(USER_LOGIN_CACHE_KEY,userId);
        if (!redisTemplate.hasKey(cacheKey)){
            return false;
        }else{
            return true;
        }
    }
}
