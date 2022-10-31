package com.bee.server.handle;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author yh
 * @date 2022/9/29 下午2:55
 */
public class HealtHandlerInitializer extends ChannelInitializer<Channel> {
    private static final int READ_TIMEOUT=4;
    private static final int Write_Timeout=6;
    private static final int ALL_IDEL_TIME_OUT=7;
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new IdleStateHandler(READ_TIMEOUT,Write_Timeout,ALL_IDEL_TIME_OUT));

    }
}
