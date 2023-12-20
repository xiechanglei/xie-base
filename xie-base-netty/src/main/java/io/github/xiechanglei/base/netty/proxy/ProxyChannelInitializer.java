package io.github.xiechanglei.base.netty.proxy;

import io.github.xiechanglei.base.netty.annotation.NettyTcpProxy;
import io.github.xiechanglei.base.netty.codec.ByteCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProxyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private NettyTcpProxy nettyTcpProxy;
    private Object proxyHandler;

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(new ByteCodec());
        ch.pipeline().addLast(new ProxyChannelInboundHandler(nettyTcpProxy,proxyHandler));
    }
}
