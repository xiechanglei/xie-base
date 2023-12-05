package com.xiechanglei.code.base.common.digest.crc;

/**
 * CRC16校验
 */
public class CRC16 {
    private static final int POLYNOMIAL = 0xA001;

    /**
     * 静态方法，直接计算CRC16
     */
    public static int calculate(byte[] data) {
        int crc = 0xFFFF;
        for (byte b : data) {
            crc = _ca(crc, b);
        }
        return crc;
    }

    public static byte[] calculateToByteArray(byte[] data) {
        int crc = calculate(data);
        return new byte[]{(byte) (crc & 0xFF), (byte) ((crc >> 8) & 0xFF)};
    }

    int crc = 0xFFFF;

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

    public int getValue() {
        return this.crc;
    }

    public byte[] getByteArrayValue() {
        return new byte[]{(byte) (this.crc & 0xFF), (byte) ((this.crc >> 8) & 0xFF)};
    }

    private static int _ca(int c, byte b) {
        c ^= b & 0xFF;
        for (int i = 0; i < 8; i++) {
            if ((c & 1) == 1) {
                c = (c >>> 1) ^ POLYNOMIAL;
            } else {
                c = c >>> 1;
            }
        }
        return c;
    }
}