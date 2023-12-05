package com.xiechanglei.code.base.common;

/**
 * 字节数组工具类
 */
public class ByteArrayHelper {
    /**
     * 将int数组转换为byte数组
     * for example:
     * byte[] bytes = ByteArrayHelper.of(0xFE, 0xFD);
     */
    public static byte[] of(int... bytes) {
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) bytes[i];
        }
        return result;
    }

    /**
     * 将16进制字符串转换为byte数组
     */
    public static byte[] of(String hexString) {
        if (hexString == null) {
            return new byte[0];
        }
        hexString = hexString.replaceAll("[\\s\r]+", "");
        if (hexString.isEmpty()) {
            return new byte[0];
        }
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("hexString length must be even");
        }
        byte[] result = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            result[i / 2] = (byte) Integer.parseInt(hexString.substring(i, i + 2), 16);
        }
        return result;
    }

    /**
     * 将字节数组转换为16进制字符串
     */
    public static String toHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {//补充0
            String hexString = Integer.toHexString(b & 0xFF);
            sb.append(hexString.length() == 1 ? "0" + hexString : hexString);
        }
        return sb.toString();
    }

    /**
     * 判断两个字节数组是否相等
     */
    public static boolean isSame(byte[] bytes1, byte[] bytes2) {
        if (bytes1 == null && bytes2 == null) {
            return true;
        }
        if (bytes1 == null || bytes2 == null) {
            return false;
        }
        if (bytes1.length != bytes2.length) {
            return false;
        }
        for (int i = 0; i < bytes1.length; i++) {
            if (bytes1[i] != bytes2[i]) {
                return false;
            }
        }
        return true;
    }

    public static short toShort(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return 0;
        }
        short result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result += (short) ((short) (bytes[i] & 0xFF) << (8 * (bytes.length - i - 1)));
        }
        return result;
    }

    /**
     * 将字节数组转换为int
     */
    public static int toInt(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result += (bytes[i] & 0xFF) << (8 * (bytes.length - i - 1));
        }
        return result;
    }

    public static byte[] fromInt(int value) {
        return new byte[]{(byte) ((value >> 24) & 0xFF), (byte) ((value >> 16) & 0xFF), (byte) ((value >> 8) & 0xFF), (byte) (value & 0xFF)};
    }

    public static byte[] fromShort(short value) {
        return new byte[]{(byte) ((value >> 8) & 0xFF), (byte) (value & 0xFF)};
    }

    public static byte[] fromLong(long value) {
        return new byte[]{(byte) ((value >> 56) & 0xFF), (byte) ((value >> 48) & 0xFF), (byte) ((value >> 40) & 0xFF), (byte) ((value >> 32) & 0xFF), (byte) ((value >> 24) & 0xFF), (byte) ((value >> 16) & 0xFF), (byte) ((value >> 8) & 0xFF), (byte) (value & 0xFF)};
    }

    /**
     * 将字节数组转换为long
     */
    public static long toLong(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return 0;
        }
        long result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result += (long) (bytes[i] & 0xFF) << (8 * (bytes.length - i - 1));
        }
        return result;
    }

}
