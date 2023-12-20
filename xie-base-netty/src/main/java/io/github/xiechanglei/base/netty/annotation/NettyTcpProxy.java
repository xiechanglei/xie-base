package io.github.xiechanglei.base.netty.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * tcp 代理
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface NettyTcpProxy {
    /**
     * 服务端地址
     */
    String remoteHost();

    /**
     * 远程端口
     */
    int remotePort();

    /**
     * 本地端口
     */
    int localPort();

    /**
     * 错误后是否重试
     */
    boolean tryAfterFail() default true;

    /**
     * 重试间隔时间,毫秒
     */
    long retryInterval() default 3000;
}
