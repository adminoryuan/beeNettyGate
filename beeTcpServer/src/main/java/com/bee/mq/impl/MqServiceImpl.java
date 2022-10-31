package com.bee.mq.impl;

 import com.bee.mq.MqService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
 import com.rabbitmq.client.MessageProperties;
 import org.springframework.stereotype.Service;

 import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yh
 * @date 2022/9/25 下午5:01
 */
@Service
public class MqServiceImpl implements MqService {

    private final String ExchangeName="Dbquque";
    private final String QuqueName="Dbquque";

    private Channel channel;

    public MqServiceImpl() throws IOException, TimeoutException {
        this.channel = MqFactory.GetConnection().createChannel();;

        //启动时创建交换机
        channel.queueDeclare(QuqueName,true,false,false,null);

    }

    @Override
    public void PushlishDbExchange(byte[] body) throws IOException, TimeoutException {
        channel.basicPublish("",ExchangeName, MessageProperties.PERSISTENT_TEXT_PLAIN,body);
    }
}
