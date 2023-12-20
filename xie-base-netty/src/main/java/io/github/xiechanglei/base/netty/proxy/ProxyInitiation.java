package io.github.xiechanglei.base.netty.proxy;

import io.github.xiechanglei.base.netty.annotation.NettyTcpProxy;
import io.github.xiechanglei.base.netty.init.NettyServerBuilder;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class ProxyInitiation implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initNettyTcpProxy(applicationContext);
    }

    public void initNettyTcpProxy(ApplicationContext applicationContext) {
        applicationContext.getBeansWithAnnotation(NettyTcpProxy.class).forEach((k, v) -> {
            NettyTcpProxy nettyTcpProxy = v.getClass().getAnnotation(NettyTcpProxy.class);
            log.info("创建NettyTcp代理,代理地址:{},代理端口:{},本地端口:{}", nettyTcpProxy.remoteHost(), nettyTcpProxy.remotePort(), nettyTcpProxy.localPort());
            new Thread(() -> {
                try {
                    while (true) {
                        try {
                            NettyServerBuilder.create(nettyTcpProxy.localPort(), NioServerSocketChannel.class, 0, 0, new ProxyChannelInitializer(nettyTcpProxy,v));
                            break;
                        } catch (Exception e) {
                            if (nettyTcpProxy.tryAfterFail()) {
                                log.error("启动netty服务端失败,端口:{},{}毫秒后重试", nettyTcpProxy.localPort(), nettyTcpProxy.retryInterval());
                                Thread.sleep(nettyTcpProxy.retryInterval());
                            } else {
                                log.error("启动netty服务端失败,端口:{}，未启用失败后重试，退出创建过程", nettyTcpProxy.localPort());
                                throw new Exception(e);
                            }
                        }
                    }

                } catch (Exception e) {
                    log.error("创建NettyTcp代理失败", e);
                }
            }).start();
        });
    }
}
