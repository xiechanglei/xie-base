package io.github.xiechanglei.base.netty.helper;

import io.netty.buffer.ByteBuf;

public class ByteBufferHelper {
    public static byte[] readBytes(ByteBuf buf, int length) {
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        return bytes;
    }
}
