package com.bee.server.impl;

import com.bee.Cache.ObjectCache;
import com.bee.codec.JsonFreamEncoder;
import com.bee.codec.ProtoBufUserDecoder;
import com.bee.server.BeeService;
import com.bee.server.handle.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/9/25 下午2:57
 */
public class BeeServiceImpl implements BeeService {

        private Logger logger=Logger.getLogger(BeeService.class.toString());
        private final String addr="127.0.0.1";
        private final int Tcpport=9091;
        private final int WebsocketPort=9092;

         @Override
        public void StartTcpService() {
            EventLoopGroup boosGroup = new NioEventLoopGroup(1);
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            try {
                serverBootstrap.group(boosGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 1024)//标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。主要是作用于boss线程，用于处理新连接
                        .childOption(ChannelOption.SO_KEEPALIVE, true)//启用心跳保活机制,主要作用与worker线程，也就是已创建的channel。
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline()
                                        .addLast(new ProtoBufUserDecoder())
                                        .addLast(ObjectCache.getRelayServerHandle())
                                        .addLast(ObjectCache.getTcpServer())
                                       .addLast(new HealtHandlerInitializer());
                            }
                        });

                //绑定端口,这里用到的是ChannelFuture类
                ChannelFuture future = serverBootstrap.bind(addr,Tcpport).sync();
                logger.info(String.format("Tcp服务端启动成功server=[%s]--port[%d]....",addr,Tcpport));

                //关闭通道
                future.channel().closeFuture().sync().addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        System.out.println("服务端关闭了！");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //在这里关闭事件线程组,释放资源
                boosGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }


        @Override
        public void StartWebsocketService() {
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast(new HttpServerCodec()); // HTTP 协议解析，用于握手阶段
                                pipeline.addLast(new HttpObjectAggregator(65536)); // HTTP 协议解析，用于握手阶段
                               // pipeline.addLast(new WebSocketServerCompressionHandler()); // WebSocket 数据压缩扩展
                                pipeline.addLast(new HttpServerHandle());
                                pipeline.addLast(new WebSocketServerProtocolHandler("/", null, true)); // WebSocket 握手、控制帧处理
                                pipeline.addLast(new JsonFreamEncoder());
                                pipeline.addLast(new WebSocketServerHandler());
                            }
                        });

                ChannelFuture f = b.bind(WebsocketPort).sync();
                logger.info(String.format("websocket服务端启动成功server=[%s]--port[%d]....",addr,WebsocketPort));

                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        }
}
