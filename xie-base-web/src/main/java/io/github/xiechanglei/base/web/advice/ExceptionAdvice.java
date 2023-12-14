package io.github.xiechanglei.base.web.advice;

import io.github.xiechanglei.base.common.bean.message.MessageException;
import io.github.xiechanglei.base.common.bean.message.MessageResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static io.github.xiechanglei.base.common.bean.message.MessageResponse.UNKNOWN_ERROR_CODE;

/**
 * 统一异常处理
 */
@RestControllerAdvice
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.web", name = "exception-advice", havingValue = "true", matchIfMissing = true)
@Log4j2
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public MessageResponse defaultHandle(Exception e) {
        log.error(e);
        return MessageResponse.fail("系统错误", UNKNOWN_ERROR_CODE);
    }

    @ExceptionHandler(MessageException.class)
    public MessageResponse handleMessageException(MessageException e) {
        return MessageResponse.fail(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(BindException.class)
    public MessageResponse handle(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError == null ? "参数错误:" : fieldError.getDefaultMessage();
        return MessageResponse.fail(message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public MessageResponse handle(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        return MessageResponse.fail("参数错误:" + String.join(",", errors));
    }
}
