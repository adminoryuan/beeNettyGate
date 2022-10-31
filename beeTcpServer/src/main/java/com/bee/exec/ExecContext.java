package com.bee.exec;

import com.bee.exec.impl.AuthStrategy;
import com.bee.exec.impl.UploadStrategy;
import com.bee.untils.MessageTypeUntils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yh
 * @date 2022/9/25 下午3:30
 */

public class ExecContext {

    private final static Map<Integer,ExecStrategy> strategyMap=
                                new HashMap<>();
    static {
        strategyMap.put(MessageTypeUntils.fristId,new AuthStrategy());
        strategyMap.put(MessageTypeUntils.UploadId,new UploadStrategy());
    }

    public static   ExecStrategy getExec(int MessageId){
        return strategyMap.getOrDefault(MessageId,null);
    }
}
