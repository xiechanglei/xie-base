package com.xiechanglei.code.base.rbac.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiechanglei.code.base.common.digest.aes.AESHelper;

public class TokenHandler {
    private static final String key = "xie-rbac";
    private static final String iv = "xie-rbac-auth";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static String encode(TokenInfo token) {
        try {
            return AESHelper.encode(objectMapper.writeValueAsString(token), key, iv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TokenInfo decode(String token) {
        try {
            return objectMapper.readValue(AESHelper.decode(token, key, iv), TokenInfo.class);
        } catch (Exception e) {
            return null;
        }
    }

}
