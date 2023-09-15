package com.xiechanglei.code.base.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Date;
import java.util.Objects;

public class FeignDateTypeResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Date.class);
    }

    @Override
    public Date resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String dateStr = webRequest.getParameter(Objects.requireNonNull(parameter.getParameterName()));
        if (dateStr == null) {
            return null;
        }
        return new Date(Long.parseLong(dateStr));
    }
}
