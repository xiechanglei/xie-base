package com.xiechanglei.code.base.netty.annotation;


import com.xiechanglei.code.base.netty.init.NettyConfigBuilder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * netty终端
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface NettyClient {
    /**
     * 服务端地址
     */
    String server();

    /**
     * 服务端端口
     */
    int port();

    /**
     * channel type
     */
    ChannelType channelType() default ChannelType.NIO;

    /**
     * channelClass
     */
    Class<? extends Channel> channelClass() default NioSocketChannel.class;

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


    /**
     * 高度自定义,客户端花样比较多，所以这里提供一个自定义的接口
     */
    Class<? extends NettyConfigBuilder> configBuilder() default NettyConfigBuilder.class;
}
