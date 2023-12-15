package io.github.xiechanglei.base.common.bean.message;

import lombok.Getter;
import lombok.Setter;

import static io.github.xiechanglei.base.common.bean.message.MessageResponse.UNKNOWN_ERROR_CODE;

/**
 * 通用消息异常类
 */
@Getter
@Setter
public class MessageException extends RuntimeException {
    private int code; // 通常用于解释错误码

    public MessageException(String message) {
        this(message, UNKNOWN_ERROR_CODE);
    }

    public MessageException(String message, int code) {
        super(message);
        this.code = code;
    }

    public static MessageException of(String message) {
        return new MessageException(message);
    }

    public static MessageException of(String message, int code) {
        return new MessageException(message, code);
    }

    public static MessageException of(ErrorDefinition errorDefinition) {
        return new MessageException(errorDefinition.getMessage(), errorDefinition.getCode());
    }
}
