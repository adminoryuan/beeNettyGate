package com.bee.Cache;

import com.bee.server.handle.HealtHandlerInitializer;
import com.bee.server.handle.RelayServerHandle;
import com.bee.server.handle.TcpServerHandle;

/**
 * @author yh
 * @date 2022/10/1 上午12:06
 * 对象池
 */
public class ObjectCache {

    private static RelayServerHandle relayServerHandle=new RelayServerHandle();
    private static TcpServerHandle tcpServerHandle=new TcpServerHandle();


    public static RelayServerHandle getRelayServerHandle(){
        return relayServerHandle;
    }
    public static TcpServerHandle getTcpServer(){
        return tcpServerHandle;
    }
}
