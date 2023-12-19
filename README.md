# xie-base ![](https://img.shields.io/badge/license-Apache2.0-blue) ![](https://img.shields.io/badge/version-2.7.17.4-green) ![](https://img.shields.io/badge/java-jdk8-green) ![](https://img.shields.io/badge/springboot-2.7.17-green)

## 1. 介绍

`xie-base` 是一个基础工具类模块，提供了一些基础的工具类，以及一些通用的项目脚手架，

版本规则是 springboot 的版本号 + 自定义版本号，如：`2.7.17.4` 表示的是基于 springboot 2.7.17 的第2个版本。

## 2. 使用

### 2.1 引入单个依赖
如，使用json模块，引入依赖：
```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-json</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### 2.2 作为parent引入
xie-base 依然是一个spring boot的子模块，你可以替换掉spring boot的parent
```xml
<parent>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base</artifactId>
    <version>2.7.17.4</version>
</parent>

<dependencies>
    <dependency>
        <groupId>io.github.xiechanglei</groupId>
        <artifactId>xie-base-web</artifactId>
    </dependency>
    <dependency>
        <groupId>io.github.xiechanglei</groupId>
        <artifactId>xie-base-netty</artifactId>
    </dependency>
    <dependency>
        <groupId>io.github.xiechanglei</groupId>
        <artifactId>xie-base-common</artifactId>
    </dependency>
</dependencies>
```

## 3 模块介绍
- [x] [xie-base-common-async](./xie-base-common-async/README.md)
- [x] [xie-base-common-bean](./xie-base-common-bean/README.md)
- [x] [xie-base-common-digest](./xie-base-common-digest/README.md)
- [x] [xie-base-common-http](./xie-base-common-http/README.md)
- [x] [xie-base-common-jpa](./xie-base-common-jpa/README.md)
- [x] [xie-base-common-json](./xie-base-common-json/README.md)
- [x] [xie-base-common-reflect](./xie-base-common-reflect/README.md)
- [x] [xie-base-common-function](./xie-base-common-function/README.md)
- [x] [xie-base-common-base](./xie-base-common-base/README.md)
- [x] [xie-base-common](./xie-base-common/README.md)
- [x] [xie-base-netty](./xie-base-netty/README.md)
- [x] [xie-base-rbac](./xie-base-rbac/README.md)
- [x] [xie-base-web](./xie-base-web/README.md)