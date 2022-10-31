package com.bee.server;

/**
 * @author yh
 * @date 2022/9/25 下午2:56
 */
public interface BeeService {
    void  StartTcpService();

    void StartWebsocketService();
}
