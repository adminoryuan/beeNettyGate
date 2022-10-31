package com.bee.server.handle;

import com.alibaba.fastjson.JSONObject;
import com.bee.Cache.WebConnectCache;
import com.bee.common.userEntity;
import com.bee.untils.redisTempleUntils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;

import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/10/16 下午1:36
 */
public class HttpServerHandle extends SimpleChannelInboundHandler<HttpRequest> {
    private Logger logger=Logger.getLogger(HttpServerHandle.class.toString());
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
        String token=httpRequest.headers().get("token");
        String userJson=null;
        if ((userJson=redisTempleUntils.Get(token))==null){
            logger.info("未登录断开链接!!!");
            channelHandlerContext.channel().close();
            return;
        }


        userEntity userEntity =JSONObject.parseObject(JSONObject.parseArray(userJson).getString(1),
                                userEntity.class);

        if (WebConnectCache.isExist(userEntity.getApikey())){
            logger.info("重复登录！关闭链接");
            channelHandlerContext.channel().close();
            return;
        }
        logger.info(String.format("%s 链接了服务器", userEntity.getName()));

        WebConnectCache.put(userEntity.getApikey(),channelHandlerContext.channel());
        channelHandlerContext.fireChannelRead(httpRequest);
    }
}
