package com.bee.codec;

import com.bee.common.Collect;
import com.google.protobuf.ByteString;
import com.google.protobuf.util.JsonFormat;
import com.rabbitmq.tools.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import com.alibaba.fastjson.*;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * @author yh
 * @date 2022/10/16 下午3:10
 */
public class JsonFreamEncoder extends MessageToMessageEncoder<ByteString> {

    /**
     * 将protobuf转换为json
     * @param channelHandlerContext
     * @param bytes
     * @param list
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteString bytes, List<Object> list) throws Exception {
        Collect.collectInfo collectInfo = Collect.collectInfo.parseFrom(bytes.toByteArray());
        String s = JsonFormat.printer().print(collectInfo);

        list.add(new TextWebSocketFrame(s));
    }
}
