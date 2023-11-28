package com.xiechanglei.code.test;

import com.xiechanglei.code.base.netty.annotation.NettyServer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ChannelHandler.Sharable
@NettyServer(port = 8080, handler = {StringDecoder.class, StringEncoder.class})
public class TestServerClient extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        log.info("接收到数据:{}", msg);
        ctx.writeAndFlush("server get message success:" + msg + "\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("连接成功");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx)  {
        log.info("连接断开");
    }
}
