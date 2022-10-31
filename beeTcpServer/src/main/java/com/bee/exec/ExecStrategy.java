package com.bee.exec;

import com.bee.common.TranMessage;
import com.google.protobuf.ByteString;
import io.netty.channel.Channel;

import java.nio.channels.SocketChannel;

/**
 * @author yh
 * @date 2022/9/25 下午3:29
 */
public interface ExecStrategy {

    void Exec(TranMessage.Message message, Channel socketChannel) throws Exception;
}
