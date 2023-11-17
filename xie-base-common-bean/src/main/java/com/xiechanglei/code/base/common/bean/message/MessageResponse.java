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
    private Boolean success;
    // 返回数据
    private Object data;
    // 返回消息
    private String message;

    public static MessageResponse success(Object data) {
        return new MessageResponse(true, data, null);
    }

    public static MessageResponse fail(String message) {
        return new MessageResponse(false, null, message);
    }
}
