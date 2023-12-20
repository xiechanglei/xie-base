package io.github.xiechanglei.base.netty.proxy;

import io.github.xiechanglei.base.netty.annotation.NettyTcpProxy;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

@Log4j2
@ChannelHandler.Sharable
public class ProxyChannelInboundHandler extends SimpleChannelInboundHandler<byte[]> {
    private final NettyTcpProxy nettyTcpProxy;
    private ProxyHandler proxyHandler;
    private Socket socket = null;
    private OutputStream outputStream = null;
    private InputStream inputStream = null;

    public ProxyChannelInboundHandler(NettyTcpProxy nettyTcpProxy, Object handler) {
        this.nettyTcpProxy = nettyTcpProxy;
        if (handler instanceof ProxyHandler) {
            proxyHandler = (ProxyHandler) handler;
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws IOException {
        if (proxyHandler != null) {
            msg = proxyHandler.convertSend(msg);
        }
        outputStream.write(msg);
        outputStream.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("get proxy client");
        super.channelActive(ctx);
        this.socket = new Socket(nettyTcpProxy.remoteHost(), nettyTcpProxy.remotePort());
        this.outputStream = this.socket.getOutputStream();
        this.inputStream = this.socket.getInputStream();
        new Thread(() -> {
            byte[] temp = new byte[1024 * 10];
            while (true) {
                try {
                    int read = inputStream.read(temp);
                    if (read == -1) {
                        break;
                    }
                    byte[] msg = Arrays.copyOf(temp, read);
                    if (proxyHandler != null) {
                        msg = proxyHandler.convertReceived(msg);
                    }
                    ctx.writeAndFlush(msg);
                } catch (Exception ignore) {
                    ctx.close();
                }
            }
        }).start();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        socket.close();
        log.info("release proxy client");
    }
}
