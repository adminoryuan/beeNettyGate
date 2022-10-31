package com.example.beeboot.manager;

import com.example.beeboot.Entity.userEntity;

/**
 * @author yh
 * @date 2022/9/26 下午1:27
 */
public interface TokenManager {
    /**
     * 创建token
     * @param userInfo
     * @return
     */
    String getToken(userEntity userInfo);

    /**
     * 刷新用户
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     * @param token
     */
    void loginOff(String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    userEntity getUserInfoByToken(String token);
}
