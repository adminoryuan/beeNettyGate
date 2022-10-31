package com.bee;

import com.bee.server.BeeService;
import com.bee.server.impl.BeeServiceImpl;
import com.bee.untils.SpringBeanUntils;
import com.alibaba.fastjson.*;
/**
 * @author yh
 * @date 2022/9/25 下午3:15
 */
public class Run {
    public static void main(String[] args) {
        SpringBeanUntils.load();
        BeeService beeService = new BeeServiceImpl();
        new Thread(new Runnable() {
            @Override
            public void run() {
                beeService.StartTcpService();
            }
        }).start();
        beeService.StartWebsocketService();

    }
}
