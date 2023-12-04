package com.xiechanglei.code.base.netty.init;

import com.xiechanglei.code.base.netty.annotation.NettyClient;
import com.xiechanglei.code.base.netty.annotation.NettyServer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Log4j2
public class NettyInitiation implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initNettyServer(applicationContext);
        initNettyClient(applicationContext);
    }

    public void initNettyServer(ApplicationContext applicationContext) {
        applicationContext.getBeansWithAnnotation(NettyServer.class).forEach((k, v) -> new Thread(() -> {
            try {
                NettyServer nettyServer = v.getClass().getAnnotation(NettyServer.class);
                log.info("创建Netty服务端,端口:{}", nettyServer.port());
                while (true) {
                    try {
                        //下面的代码会阻塞，所以放在单独的线程中执行
                        NettyServerBuilder.create(nettyServer.port(), nettyServer.channelClass(), nettyServer.bossThreadCount(), nettyServer.workerThreadCount(), new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch){
                                getChannelHandlers(nettyServer.handler()).forEach(ch.pipeline()::addLast);
                                ch.pipeline().addLast((ChannelHandler) v);
                            }
                        });
                        break;
                    } catch (Exception e) {
                        if (nettyServer.tryAfterFail()) {
                            log.error("启动netty服务端失败,端口:{},{}毫秒后重试", nettyServer.port(), nettyServer.retryInterval());
                            Thread.sleep(nettyServer.retryInterval());
                        } else {
                            log.error("启动netty服务端失败,端口:{}，未启用失败后重试，退出创建过程", nettyServer.port());
                            throw new Exception(e);
                        }
                    }
                }

            } catch (Exception e) {
                log.error("创建Netty服务端失败", e);
            }

        }).start());
    }

    public void initNettyClient(ApplicationContext applicationContext) {
        applicationContext.getBeansWithAnnotation(NettyClient.class).forEach((k, v) -> new Thread(() -> {
            try {
                NettyClient nettyClient = v.getClass().getAnnotation(NettyClient.class);
                log.info("创建Netty客户端,服务器:{},端口:{}", nettyClient.server(), nettyClient.port());
                NettyConfigBuilder nettyConfigBuilder = getNettyConfigBuilder(nettyClient.configBuilder(), applicationContext);
                while (true) {
                    try {
                        //下面的代码会阻塞，所以放在单独的线程中执行
                        NettyClientBuilder.create(nettyClient.server(), nettyClient.port(), nettyClient.channelType(), nettyClient.channelClass(), nettyConfigBuilder,new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch){
                                getChannelHandlers(nettyClient.handler()).forEach(ch.pipeline()::addLast);
                                ch.pipeline().addLast((ChannelHandler) v);
                            }
                        });
                        break;
                    } catch (Exception e) {
                        if (nettyClient.tryAfterFail()) {
                            log.error("创建Netty客户端,服务器:{},端口:{},{}毫秒之后重试", nettyClient.server(), nettyClient.port(), nettyClient.retryInterval());
                            Thread.sleep(nettyClient.retryInterval());
                        } else {
                            log.error("Netty客户端连接失败,服务器:{},端口:{}，未启用失败后重试，退出创建过程", nettyClient.server(), nettyClient.port());
                            throw new Exception(e);
                        }
                    }
                }

            } catch (Exception e) {
                log.error("创建Netty客户端失败", e);
            }

        }).start());
    }

    public NettyConfigBuilder getNettyConfigBuilder(Class<? extends NettyConfigBuilder> builderClass, ApplicationContext applicationContext)
            throws InstantiationException, IllegalAccessException {
        NettyConfigBuilder bean = null;
        try {
            // 尝试从spring容器中获取
            bean = applicationContext.getBean(builderClass);
        } catch (Exception ignored) {
            log.info("未找到NettyConfigBuilder的实现类,使用默认实现类:{}", builderClass.getName());
        }
        if (bean == null && builderClass != NettyConfigBuilder.class) {
            bean = builderClass.newInstance();
        }

        return bean;
    }


    public List<ChannelHandler> getChannelHandlers(Class<?>... handlerClasses) {
        List<ChannelHandler> handlers = new ArrayList<>();
        for (Class<?> hClass : handlerClasses) {
            try {
                handlers.add((ChannelHandler) hClass.newInstance());
            }catch (Exception ignored){}
        }
        return handlers;
    }
}
