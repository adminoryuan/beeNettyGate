package com.bee.exec.impl;

import com.bee.common.Collect;
import com.bee.common.TranMessage;
import com.bee.exec.ExecStrategy;
import com.bee.mq.MqService;
import com.bee.mq.impl.MqServiceImpl;
import com.bee.untils.MessageTypeUntils;
import com.bee.untils.SpringBeanUntils;
import com.bee.untils.redisTempleUntils;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/9/25 下午3:29
 */
public class UploadStrategy implements ExecStrategy{

    MqService service= SpringBeanUntils.getBean(MqService.class);

    private Logger logger=Logger.getLogger(UploadStrategy.class.toString());

    /**
     * 首次登录时鉴权
     * @param message
     * @param socketChannel
     * @throws Exception
     */

    public void Exec(TranMessage.Message message, Channel socketChannel) throws Exception {

        if (message.getMessageId()!= MessageTypeUntils.UploadId)
            throw new Exception("消息类型错误！");

        service.PushlishDbExchange(message.toByteArray());
    }




}
