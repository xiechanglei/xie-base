package com.xiechanglei.code.base.web.advice;

public class MessageException extends RuntimeException{
    public MessageException(String message) {
        super(message);
    }

    public static MessageException of(String message) {
        return new MessageException(message);
    }
}
