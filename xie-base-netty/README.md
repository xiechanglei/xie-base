# xie-base-netty

`xie-base-netty` 是基于spring-boot-starter 的一个脚手架,封装了一些常用的操作

引入依赖:

```xml

<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-netty</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### 启动一个nettyServer

```java

@NettyServer(port = 9090, handler = {StringDecoder.class, StringEncoder.class})
public class MyNettyServer implements SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) {
        log.info("接收到数据:{}", s);
        ctx.writeAndFlush("hello :" + s);
    }
}
```

### 启动一个nettyClient

创建一个连接到指定服务器的客户端，默认会自动进行断线重连

```java

@NettyClient(server = "127.0.0.1", port = 9090, handler = {StringDecoder.class, StringEncoder.class})
public class MyNettyServer implements SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) {
        log.info("接收到数据:{}", s);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("zhangsan");
    }

}
```

### ByteBufferHelper

ByteBuf 工具类

```java
byte[] bytes=ByteBufferHelper.readBytes(buf,100);
```

### NettyTcpProxy
tcp代理
```java
@NettyTcpProxy(remoteHost = "127.0.0.1", remotePort = 3306, localPort = 3307)
public class TcpProxy {
    //在3306端口启动tcp服务，转发到127.0.0.1 的 3306端口
}
```

如果你需要对数据进行一些处理：
```java

@NettyTcpProxy(remoteHost = "127.0.0.1", remotePort = 3306, localPort = 3307)
public class TcpProxy implements ProxyHandler {
    @Override
    public byte[] convertSend(byte[] source) {
        //处理发送给服务端的数据
        System.out.println(source.length);
        return source;
    }
    @Override
    public byte[] convertReceived(byte[] source) {
        //处理发送给客户端的数据
        System.out.println(source.length);
        return source;
    }
}
```