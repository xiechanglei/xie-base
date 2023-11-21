package com.xiechanglei.code.base.common.bean.message;

public class MessageException extends RuntimeException{
    public MessageException(String message) {
        super(message);
    }

    public static MessageException of(String message) {
        return new MessageException(message);
    }
}
