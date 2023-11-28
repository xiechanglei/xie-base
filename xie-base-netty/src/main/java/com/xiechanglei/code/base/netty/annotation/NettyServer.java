package com.xiechanglei.code.base.netty.annotation;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * netty服务端
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface NettyServer {
    /**
     * 服务端端口
     */
    int port();

    /**
     * boss线程数
     */
    int bossThreadCount() default 0;

    /**
     * worker线程数
     */
    int workerThreadCount() default 0;

    /**
     * channel type
     */
    Class<? extends ServerChannel> channelClass() default NioServerSocketChannel.class;

    /**
     * 错误后是否重试
     */
    boolean tryAfterFail() default true;

    /**
     * 重试间隔时间,毫秒
     */
    long retryInterval() default 3000;

    /**
     * ChannelHandler
     */
    Class<? extends ChannelHandler>[] handler() default {};
}
