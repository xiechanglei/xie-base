package com.xiechanglei.code.base.common.digest.md5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5 加密工具类
 */
public class Md5Helper {
    /**
     * MD5加密
     *
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                int val = b & 0xff;
                if (val < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }


}
