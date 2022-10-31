package com.example.beeboot.Service.impl;

import com.example.beeboot.Context.response.LoginResponse;
import com.example.beeboot.Entity.userEntity;
import com.example.beeboot.Mapper.userMapper;
import com.example.beeboot.Service.UserService;
import com.example.beeboot.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author yh
 * @date 2022/9/26 下午12:53
 */
@Service
public class UserServiceImpl implements UserService {

    private TokenManager manager;

    private userMapper mapper;

    public UserServiceImpl(TokenManager manager, userMapper mapper) {
        this.manager = manager;
        this.mapper = mapper;
    }

    @Override
    public LoginResponse Login(String admin, String password) {
        userEntity userOne = mapper.getUserOne(admin, password);
        if (userOne==null){
            return null;
        }

        String token = manager.getToken(userOne);
        return LoginResponse.
                builder()
                .Name(userOne.getAdmin())
                .Token(token).build();

    }

    @Override
    public boolean regist(userEntity entity) {
        entity.setApikey(UUID.randomUUID().toString());
        int i = mapper.addUser(entity);
        return i>0;
    }
}
