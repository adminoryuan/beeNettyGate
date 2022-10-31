package com.bee.server.handle;

import com.bee.Cache.WebConnectCache;
import com.bee.common.TranMessage;
import com.bee.exec.ExecContext;
import com.bee.exec.ExecStrategy;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/9/25 下午2:58
 */
@ChannelHandler.Sharable
public class TcpServerHandle extends ChannelInboundHandlerAdapter {

    private Logger logger=Logger.getLogger(TcpServerHandle.class.toString());


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent evt1 = (IdleStateEvent) evt;
            if (evt1.state()== IdleState.WRITER_IDLE){
                //超时断开链接
                ChannelFuture closeFature = ctx.channel().close();
                closeFature.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        //清除缓存
                        WebConnectCache.remove(channelFuture.channel());
                    }
                });
            }
        }else
            super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        logger.info("publish db");
        TranMessage.Message msg1 =(TranMessage.Message) msg;


        ExecStrategy exec = ExecContext.
                            getExec(msg1.getMessageId());

        if (exec!=null){
            try {
                exec.Exec(msg1,ctx.channel());
            } catch (Exception e) {
                logger.info(e.getMessage());
                e.printStackTrace();
            }
        }

        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("新增链接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        logger.info("链接断开");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}