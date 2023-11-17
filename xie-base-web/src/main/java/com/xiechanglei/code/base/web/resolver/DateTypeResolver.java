package com.xiechanglei.code.base.web.resolver;

import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 解析网页端传递的时间参数，格式为yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd
 */

@Component
public class DateTypeResolver implements HandlerMethodArgumentResolver {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter shortDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //gmt+8
    private static final ZoneId zoneId = ZoneId.of("GMT+8");

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Date.class);
    }

    @Override
    public Date resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, @Nonnull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String paramName = parameter.getParameterName();
        String dateStr = webRequest.getParameter(paramName == null ? "" : paramName);
        if (!StringUtils.hasText(dateStr)) {
            return null;
        }
        if (dateStr.contains("-")) {
            if (dateStr.length() > 10) {
                return Date.from(LocalDateTime.parse(dateStr, dateFormat).atZone(zoneId).toInstant());
            } else {
                return Date.from(LocalDate.parse(dateStr, shortDateFormat).atStartOfDay(zoneId).toInstant());
            }
        } else {
            return new Date(Long.parseLong(dateStr));
        }
    }

}
