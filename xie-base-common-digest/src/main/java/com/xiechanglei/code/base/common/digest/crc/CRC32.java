package com.xiechanglei.code.base.common.digest.crc;

/**
 * CRC32校验
 */
public class CRC32 {

    /**
     * 静态方法，直接计算CRC32
     */
    public static long calculate(byte[] data) {
        long crc = 0xffffffffL;
        for (byte b : data) {
            crc = _ca(crc, b);
        }
        return crc;
    }

    public static byte[] calculateToByteArray(byte[] data) {
        long crc = calculate(data);
        return new byte[]{(byte) (crc & 0xFF), (byte) ((crc >> 8) & 0xFF), (byte) ((crc >> 16) & 0xFF), (byte) ((crc >> 24) & 0xFF)};
    }

    long crc = 0xffffffffL;

    public byte[] update(byte[] data) {
        if (data != null) {
            for (byte b : data) {
                update(b);
            }
        }
        return data;
    }

    public byte update(byte b) {
        this.crc = _ca(this.crc, b);
        return b;
    }

    public long getValue() {
        return this.crc;
    }

    public byte[] getByteArrayValue() {
        return new byte[]{(byte) (this.crc & 0xFF), (byte) ((this.crc >> 8) & 0xFF), (byte) ((this.crc >> 16) & 0xFF), (byte) ((this.crc >> 24) & 0xFF)};
    }

    private static long _ca(long c, byte b) {
        c ^= b & 0xFF;
        for (int i = 0; i < 8; i++) {
            if ((c & 1) == 1) {
                c = (c >>> 1) ^ 0xEDB88320L;
            } else {
                c = c >>> 1;
            }
        }
        return c;
    }

}