package com.xiechanglei.code.base.rbac;

import com.xiechanglei.code.base.rbac.controller.InternalControllerMessageBuilder;
import com.xiechanglei.code.base.rbac.exception.NoPermissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@ConditionalOnBean(InternalControllerMessageBuilder.class)
public class WebExceptionAdvice {
    private final InternalControllerMessageBuilder internalControllerMessageBuilder;

    @ExceptionHandler(NoPermissionException.class)
    public Object defaultErrorHandler(Exception e) {
        return internalControllerMessageBuilder.permissionDenied();
    }
}
