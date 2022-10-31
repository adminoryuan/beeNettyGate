package com.example.beeboot.Mapper;

import com.example.beeboot.Entity.userEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yh
 * @date 2022/9/26 下午12:53
 */
@Mapper
public interface userMapper {

    userEntity getUserOne(@Param("admin") String admin, String passwd);

    int addUser(userEntity entity);
}
