package com.bee.exec.impl;

import com.bee.common.TranMessage;
import com.bee.exec.ExecStrategy;
import com.bee.mq.MqService;
import com.bee.untils.MessageTypeUntils;
import com.bee.untils.SpringBeanUntils;
import com.bee.untils.redisTempleUntils;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/10/14 下午1:47
 */
public class AuthStrategy implements ExecStrategy {

    private Logger logger=Logger.getLogger(AuthStrategy.class.toString());

    MqService service= SpringBeanUntils.getBean(MqService.class);

    /**
     * 首次登录时鉴权
     * @param message
     * @param socketChannel
     * @throws Exception
     */
    @Override
    public void Exec(TranMessage.Message message, Channel socketChannel) throws Exception {
        logger.info("[AuthStraegy]---------"+message.getApikey());
        if (message.getMessageId()!= MessageTypeUntils.fristId)
            throw new Exception("消息类型错误！");

        String body;
        if ((body=redisTempleUntils.Get(message.getApikey()))==null){

            logger.info("当前用户不存在！,已断开链接");
            socketChannel.close();
            return;
        }
        //将消息同步到数据库中
        service.PushlishDbExchange(message.toByteArray());

    }

}
