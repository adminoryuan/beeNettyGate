package com.beedbclient;

import com.beedbclient.Service.DbService;
import com.beedbclient.Service.impl.DbServiceImpl;
import com.beedbclient.common.TranMessage;
import com.beedbclient.custmoer.MqFactory;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/9/25 下午6:02
 */
public class Run {
    private static final String qName="Dbquque";
    private static Logger logger=Logger.getLogger(Run.class.toString());
    private static final DbService dbServer=new DbServiceImpl();
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = MqFactory.GetConnection().createChannel();
        Consumer consumer = new DefaultConsumer(channel)
        {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
            {
                TranMessage.Message message = TranMessage.Message.parseFrom(body);
                boolean res=true;
                if (message.getMessageId()==1)
                    res= dbServer.AddCollItem(message);
                if (message.getMessageId()==0)
                   res= dbServer.AddComputerInfo(message);

                channel.basicAck(envelope.getDeliveryTag(), res);
            }
        };
        channel.basicConsume(qName,consumer);
    }
}
