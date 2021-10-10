package com.herong.demo.service;

import com.herong.demo.cache.UserLoginCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLoginCacheServiceTest {

    @Autowired
    private UserLoginCacheService userLoginCacheService;

    @Test
    public void cacheServiceTest(){
        userLoginCacheService.addUserLoginCache(11L,"hello");
        Boolean result = userLoginCacheService.isUserLoginByWx(11L);
        System.out.println(result);
    }
}
