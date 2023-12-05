package com.xiechanglei.code.base.common.digest.crc;

/**
 * CRC32校验
 */
public class CRC32 {

    /**
     * 静态方法，直接计算CRC32
     */
    public static long calculate(byte[] data) {
        CRC32 crc321 = new CRC32();
        crc321.update(data);
        return crc321.getValue();
    }

    public static byte[] calculateToByteArray(byte[] data) {
        return crc32ToByteArray(calculate(data));
    }

    java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();

    public byte[] update(byte[] data) {
        crc32.update(data);
        return data;
    }

    public byte update(byte b) {
        crc32.update(b);
        return b;
    }

    public long getValue() {
        return this.crc32.getValue();
    }

    public byte[] getByteArrayValue() {
        long crc = getValue();
        return crc32ToByteArray(crc);
    }

    private static byte[] crc32ToByteArray(long crc) {
        return new byte[]{(byte) ((crc >> 24) & 0xFF), (byte) ((crc >> 16) & 0xFF), (byte) ((crc >> 8) & 0xFF), (byte) (crc & 0xFF)};
    }
}