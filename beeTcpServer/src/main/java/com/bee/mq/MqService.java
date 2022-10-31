package com.bee.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yh
 * @date 2022/9/25 下午4:59
 */
public interface MqService {

    /*
    * 将消息投递到db对应的交换机
    *
     */
    void PushlishDbExchange(byte[] body) throws IOException, TimeoutException;

}
