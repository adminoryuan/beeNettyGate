package com.beedbclient.Entity;

import lombok.Builder;
import lombok.Getter;

/**
 * @author yh
 * @date 2022/9/25 下午6:13
 */
@Getter
@Builder
public class CollectionEntity {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    private String sys_memory;
    private String swap_memory;
    private int disk;
    private String sys_cpu;
    private String us_cpu;
    private String io_cpu;
    private String apiKey;
    private String node;
}
