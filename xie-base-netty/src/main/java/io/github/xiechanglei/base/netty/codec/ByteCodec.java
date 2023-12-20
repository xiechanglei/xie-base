package io.github.xiechanglei.base.netty.codec;

import io.netty.handler.codec.ByteToMessageCodec;
public class ByteCodec extends ByteToMessageCodec<byte[]> {
    @Override
    protected void encode(io.netty.channel.ChannelHandlerContext ctx, byte[] msg, io.netty.buffer.ByteBuf out) throws Exception {
        out.writeBytes(msg);
    }

    @Override
    protected void decode(io.netty.channel.ChannelHandlerContext ctx, io.netty.buffer.ByteBuf in, java.util.List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        out.add(bytes);
    }
}
