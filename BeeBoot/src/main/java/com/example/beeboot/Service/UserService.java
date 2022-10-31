package com.example.beeboot.Service;

import com.example.beeboot.Context.response.LoginResponse;
import com.example.beeboot.Entity.userEntity;

/**
 * @author yh
 * @date 2022/9/26 下午12:53
 */
public interface UserService {
    public LoginResponse Login(String admin, String password);

    boolean regist(userEntity entity);
}
