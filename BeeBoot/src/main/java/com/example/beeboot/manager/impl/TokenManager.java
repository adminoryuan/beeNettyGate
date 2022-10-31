package com.example.beeboot.manager.impl;

import com.example.beeboot.Entity.userEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/9/26 下午1:28
 */
@Component
public class TokenManager implements com.example.beeboot.manager.TokenManager {
    private Logger logger=Logger.getLogger(TokenManager.class.toString());
    @Autowired
        RedisTemplate<Object,Object> redisTemplate;

    @Override
    public String getToken(userEntity userInfo) {
        String token=CreateToken();
        redisTemplate.opsForValue().set(token,userInfo);
        logger.info(String.format("create token %s", token));
        return token;
    }
    private String CreateToken(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public void refreshUserToken(String token) {

    }

    @Override
    public void loginOff(String token) {

    }

    @Override
    public userEntity getUserInfoByToken(String token) {
        return null;
    }
}
