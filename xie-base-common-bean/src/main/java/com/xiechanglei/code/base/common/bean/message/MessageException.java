package com.xiechanglei.code.base.common.bean.message;

import lombok.Getter;
import lombok.Setter;

import static com.xiechanglei.code.base.common.bean.message.MessageResponse.UNKNOWN_ERROR_CODE;

@Getter
@Setter
public class MessageException extends RuntimeException {
    private int code;

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
}
