package com.bee.mq.impl;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yh
 * @date 2022/9/25 下午5:15
 */
public class test {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MqFactory.GetConnection();
        Channel channel = connection.createChannel();
        Consumer consumer = new DefaultConsumer(channel)
        {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
            {
                System.out.println("接收信息：" + new String(body));
                try
                {
                    TimeUnit.SECONDS.sleep(1);
                }
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume("Dbquque", consumer);
        while (true){ }
    }
}
