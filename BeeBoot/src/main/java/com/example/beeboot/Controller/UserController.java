package com.example.beeboot.Controller;

import com.example.beeboot.Context.response.LoginResponse;
import com.example.beeboot.Entity.userEntity;
import com.example.beeboot.Service.UserService;
import com.example.beeboot.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author yh
 * @date 2022/9/26 下午12:55
 */
@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/Login")
    public Result<LoginResponse> Login(@RequestParam String admin,
                                       @RequestParam String password){
        LoginResponse login = service.Login(admin, password);
        if (login==null){
            return Result.failure("账户或者密码错误");
        }
        return Result.success(login);
    }

    @PostMapping("/Regist")
    public Result<String> register(@RequestBody userEntity entity){

        if (service.regist(entity)){
            return Result.success("注册成功");
        }
        return Result.failure("注册失败,请稍后重试");

    }


}
