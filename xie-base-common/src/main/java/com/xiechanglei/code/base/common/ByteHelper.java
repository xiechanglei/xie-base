package com.xiechanglei.code.base.common;

/**
 * 字节工具类
 */
public class ByteHelper {
    /**
     * 将16进制字符串转换为byte
     */
    public static byte of(String hexString) {
        if (hexString == null || hexString.length() != 2) {
            throw new IllegalArgumentException("hexString length must be 2");
        }
        return (byte) Integer.parseInt(hexString, 16);
    }

    public static String toHexString(byte b) {
        String hexString = Integer.toHexString(b & 0xFF);
        return hexString.length() == 1 ? "0" + hexString : hexString;
    }
}
