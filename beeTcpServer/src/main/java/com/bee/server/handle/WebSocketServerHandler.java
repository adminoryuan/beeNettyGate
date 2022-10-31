package com.bee.server.handle;

 import com.bee.Cache.WebConnectCache;
 import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
 import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
 import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;

 import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/9/28 下午12:30
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private Logger log=Logger.getLogger(WebSocketServerHandler.class.toString());

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        log.info("收到消息"+ textWebSocketFrame.content().toString());

        channelHandlerContext.channel().write(new TextWebSocketFrame("hello"));
        channelHandlerContext.channel().flush();

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        WebConnectCache.put("",ctx.channel());
        super.channelActive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

       // gloablWebsocket.AddWebsocket("",ctx.channel());
        super.userEventTriggered(ctx, evt);
    }


}
