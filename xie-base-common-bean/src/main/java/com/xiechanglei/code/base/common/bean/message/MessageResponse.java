package com.xiechanglei.code.base.common.bean.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private boolean success;
    private Object data;
    private String message;

    public static MessageResponse success(Object data) {
        return new MessageResponse(true, data, null);
    }

    public static MessageResponse success(Object data, String message) {
        return new MessageResponse(true, data, message);
    }

    public static MessageResponse fail(String message) {
        return new MessageResponse(false, null, message);
    }

    public static MessageResponse fail(String message, Object data) {
        return new MessageResponse(false, data, message);
    }
}
