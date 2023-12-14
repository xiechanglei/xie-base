package io.github.xiechanglei.base.netty.init;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

public class NettyServerBuilder {
    public static void create(int port,
                              Class<? extends ServerChannel> channelClass,
                              int bossThreadCount,
                              int workerThreadCount,
                              ChannelInitializer<SocketChannel> channelInitializer) throws Exception {
        // 1.创建两个线程组，一个用于处理服务器端接收客户端连接，一个用于网络通信的读写
        EventLoopGroup bossGroup = new NioEventLoopGroup(bossThreadCount);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreadCount);
        ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workerGroup);
        // 2.设置NIO类型的channel
        bootstrap.channel(channelClass);
        // 3.设置线程队列中等待连接的个数
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        // 一般来说，一个端口释放后会等待两分钟之后才能再被使用，SO_REUSEADDR是让端口释放后立即就可以被再次使用
        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childHandler(channelInitializer);
        try {
            ChannelFuture sync = bootstrap.bind(port).sync();
            sync.channel().closeFuture().sync();
            throw new InterruptedException();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
