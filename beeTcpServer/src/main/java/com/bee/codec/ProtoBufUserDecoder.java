package com.bee.codec;

import com.bee.common.TranMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/9/25 下午4:08
 */
public class ProtoBufUserDecoder extends ByteToMessageDecoder {

    private Logger logger=Logger.getLogger(ProtoBufUserDecoder.class.toString());
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes()<5){
            return;
        }
        byte tag = byteBuf.readByte();

        int dataLength = byteBuf.readInt(); //读取长度

        byte[] body;

        if (tag!=0x39 || byteBuf.readableBytes() < dataLength) {
            byteBuf.resetReaderIndex();
            return;
        }
        byteBuf.readBytes((body = new byte[dataLength]));

        TranMessage.Message message = TranMessage.Message.parseFrom(body);

        list.add(message);


    }
}
