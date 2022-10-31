package com.example.beeboot.Context.response;

import lombok.Builder;
import lombok.Getter;

/**
 * @author yh
 * @date 2022/9/26 下午1:32
 */
@Builder
@Getter
public class LoginResponse {
    private String Token;
    private String Name;

}
