package com.xiechanglei.code.base.common.bean.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用消息响应类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    // 请求成功标注
    private boolean success;
    // 返回数据
    private Object data;
    // 返回消息
    private String message;

    /**
     * 请求成功的响应
     * @param data 返回数据
     * @return MessageResponse
     */
    public static MessageResponse success(Object data) {
        return new MessageResponse(true, data, null);
    }

    /**
     * 请求成功的响应
     * @param data 返回数据
     * @param message 返回消息
     * @return MessageResponse
     */
    public static MessageResponse success(Object data, String message) {
        return new MessageResponse(true, data, message);
    }

    /**
     * 请求失败的响应
     * @param message 返回消息
     * @return MessageResponse
     */
    public static MessageResponse fail(String message) {
        return new MessageResponse(false, null, message);
    }

    /**
     * 请求失败的响应
     * @param message 返回消息
     * @param data 返回数据
     * @return MessageResponse
     */
    public static MessageResponse fail(String message, Object data) {
        return new MessageResponse(false, data, message);
    }
}
