package com.xiechanglei.code.base.web.advice;

import com.xiechanglei.code.base.common.bean.message.MessageException;
import com.xiechanglei.code.base.common.bean.message.MessageResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@RestControllerAdvice
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.web", name = "exception-advice", havingValue = "true", matchIfMissing = true)
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public MessageResponse defaultHandle(Exception e) {
        e.printStackTrace();
        return MessageResponse.fail("系统错误");
    }

    @ExceptionHandler(MessageException.class)
    public MessageResponse handleMessageException(MessageException e) {
        return MessageResponse.fail(e.getMessage());
    }
}
