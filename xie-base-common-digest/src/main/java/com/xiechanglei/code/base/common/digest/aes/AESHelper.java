package com.xiechanglei.code.base.common.digest.aes;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * Aes加密工具类
 */
public class AESHelper {
    /**
     * Aes 加密
     *
     * @param content  明文
     * @param password 密码
     * @param iv       偏移量
     * @return 密文 base64 格式
     * @throws Exception 加密异常
     */
    public static String encode(String content, String password, String iv) throws Exception {
        byte[] encode = AESDigest.encode(content.getBytes(StandardCharsets.UTF_8),
                formatPass(password.getBytes(StandardCharsets.UTF_8)),
                formatIv(iv.getBytes(StandardCharsets.UTF_8)));
        return Base64.getEncoder().encodeToString(encode);
    }

    /**
     * @param content  密文  base64 格式
     * @param password 密码
     * @param iv       偏移量
     * @return 明文
     * @throws Exception 解密异常
     */
    public static String decode(String content, String password, String iv) throws Exception {
        byte[] decode = AESDigest.decode(Base64.getDecoder().decode(content),
                formatPass(password.getBytes(StandardCharsets.UTF_8)),
                formatIv(iv.getBytes(StandardCharsets.UTF_8)));
        return new String(decode, StandardCharsets.UTF_8);
    }

    /**
     * 密码的长度不是16的倍数，用0填充
     */
    private static byte[] formatPass(byte[] bytes) {
        int length = bytes.length % 16;
        if (length == 0) {
            return bytes;
        }
        return padding(bytes, 16 - length);
    }

    /**
     * iv 长度大于16，则裁剪，小于16则填充
     */
    private static byte[] formatIv(byte[] bytes) {
        int length = 16 - bytes.length;
        if (length == 0) {
            return bytes;
        }
        if (length < 0) {
            return Arrays.copyOf(bytes, 16);
        }
        return padding(bytes, length);

    }

    private static byte[] padding(byte[] bytes, int length) {
        byte[] res = new byte[bytes.length + length];
        System.arraycopy(bytes, 0, res, 0, bytes.length);
        return res;
    }
}
