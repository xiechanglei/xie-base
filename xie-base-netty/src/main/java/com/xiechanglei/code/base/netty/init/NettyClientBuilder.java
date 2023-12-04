package com.xiechanglei.code.base.netty.init;

import com.xiechanglei.code.base.netty.annotation.ChannelType;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

public class NettyClientBuilder {
    @SuppressWarnings("all")
    public static void create(String server,
                              int port,
                              ChannelType channelType,
                              Class<? extends Channel> channelClass,
                              NettyConfigBuilder configBuilder,
                              ChannelInitializer<SocketChannel> channelInitializer) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        try {
            // 1.设置channel类型
            bootstrap.group(channelType == ChannelType.OIO ? new OioEventLoopGroup() : new NioEventLoopGroup());
            bootstrap.channel(channelClass);
            // 3.设置线程队列中等待连接的个数
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.SO_REUSEADDR, true);
            bootstrap.handler(channelInitializer);
            if (configBuilder != null) {
                configBuilder.config(bootstrap);
            }
            ChannelFuture future = bootstrap.connect(server, port).sync();
            future.channel().closeFuture().sync();
            throw new InterruptedException();
        } finally {
            bootstrap.group().shutdownGracefully();
        }
    }
}
