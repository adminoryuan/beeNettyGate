package com.example.beeboot.Entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author yh
 * @date 2022/9/27 下午1:41
 */
@Builder
@Getter
public class ComputerEntity {
    private String apikey;
    private String Node;
    private String main_board;
    private String disk;
    private String mem;
    private String cpu_count;
    private String cpu_core;
    private LocalDateTime date= LocalDateTime.now();
}
