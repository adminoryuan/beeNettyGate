package com.bee.server.handle;

import com.bee.Cache.WebConnectCache;
import com.bee.common.TranMessage;
import com.bee.untils.MessageTypeUntils;
import io.netty.channel.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/9/30 下午11:42
 * 转发handle
 */
@ChannelHandler.Sharable
public class RelayServerHandle extends ChannelInboundHandlerAdapter {
    private Logger log=Logger.getLogger(RelayServerHandle.class.toString());
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        TranMessage.Message msg1 = (TranMessage.Message) msg;
        if (msg1.getMessageId() == MessageTypeUntils.UploadId) {
            if (WebConnectCache.isExist(msg1.getApikey())) {
                ctx.channel().eventLoop().submit(new Runnable() {
                    @Override
                    public void run() {
                        //转发
                        if (WebConnectCache.isExist(msg1.getApikey())) {
                            WebConnectCache.get(msg1.getApikey()).
                                  writeAndFlush(msg1.getBody());
                        }
                    }
                });
            }
        }
        ctx.fireChannelRead(msg);
    }
}
