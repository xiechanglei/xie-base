package io.github.xiechanglei.base.netty.proxy;

/**
 * 代理中间处理层
 * @NettyTcpProxy(remoteHost = "127.0.0.1", remotePort = 3306, localPort = 3307)
 * public class TcpProxy implements ProxyHandler{
 *     //在3306端口启动tcp服务，转发到127.0.0.1 的 3306端口
 *     public byte[] convert(byte[] source){
 *         return source;
 *     }
 * }
 */
public interface ProxyHandler {
    byte[] convertSend(byte[] source);
    byte[] convertReceived(byte[] source);
}
