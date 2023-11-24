package com.xiechanglei.code.base.common.jpa.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan
@EnableJpaRepositories
@EnableJpaAuditing
@Configuration
@PropertySource("classpath:xie.base.jpa.properties")
public class DefaultJpaPropertyConfig {
}
