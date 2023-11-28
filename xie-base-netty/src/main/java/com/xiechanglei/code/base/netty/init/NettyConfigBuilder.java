package com.xiechanglei.code.base.netty.init;

import io.netty.bootstrap.Bootstrap;

public interface NettyConfigBuilder {
    void config(Bootstrap bootstrap);
}