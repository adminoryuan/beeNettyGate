package com.example.beeboot.Entity;

import lombok.Builder;

/**
 * @author yh
 * @date 2022/9/25 下午7:36
 */
@Builder
public class ProcessinfoEntity {
    private String  pid;
    private int cid;
    private String user;
    private String command;
    private String mem;
    private String cpu;
}
