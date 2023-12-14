package io.github.xiechanglei.base.common.bean.message;

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
    // 通常用于解释错误码
    private int code;

    public static final int SUCCESS_CODE = 0;
    public static final int UNKNOWN_ERROR_CODE = -1;

    public static MessageResponse success(Object data) {
        return new MessageResponse(true, data, null, SUCCESS_CODE);
    }

    public static MessageResponse fail(String message, int code) {
        return new MessageResponse(false, null, message, code);
    }

    public static MessageResponse fail(String message) {
        return new MessageResponse(false, null, message, UNKNOWN_ERROR_CODE);
    }
}
