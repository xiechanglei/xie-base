package com.xiechanglei.code.base.netty.init;

import com.xiechanglei.code.base.netty.annotation.ChannelType;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

import java.util.List;

public class NettyClientBuilder {
    @SuppressWarnings("all")
    public static void create(String server,
                              int port,
                              ChannelType channelType,
                              Class<? extends SocketChannel> channelClass,
                              List<ChannelHandler> channelHandlers,
                              NettyConfigBuilder configBuilder
    ) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        try {
            // 1.设置channel类型
            bootstrap.group(channelType == ChannelType.OIO ? new OioEventLoopGroup() : new NioEventLoopGroup());
            bootstrap.channel(channelClass);
            // 3.设置线程队列中等待连接的个数
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.SO_REUSEADDR, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    channelHandlers.forEach(ch.pipeline()::addLast);
                }
            });
            if (configBuilder != null) {
                configBuilder.config(bootstrap);
            }
            ChannelFuture future = bootstrap.connect(server, port).sync();
            future.channel().closeFuture().sync();
            throw new InterruptedException();
        } finally {
            //
            bootstrap.group().shutdownGracefully();
        }
    }
}
