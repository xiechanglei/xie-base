package com.xiechanglei.code.base.web.resolver;

import com.xiechanglei.code.base.web.properties.BaseWebConfigProperties;
import org.springframework.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

/**
 * 解析网页端传递的分页参数，page和size，如果不传递则使用默认值
 */
@Component
@RequiredArgsConstructor
public class PageTypeResolver implements HandlerMethodArgumentResolver {
    private final BaseWebConfigProperties baseWebConfigProperties;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.equals(parameter.getParameterName(), "page") || Objects.equals(parameter.getParameterName(), "size");
    }

    @Override
    public Integer resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String parameterName = parameter.getParameterName();
        if (parameterName == null) {
            return null;
        }
        String str = webRequest.getParameter(parameterName);
        if (str == null) {
            if (parameterName.equals("page")) {
                return baseWebConfigProperties.getPageDefaultPage();
            }
            if (parameterName.equals("size")) {
                return baseWebConfigProperties.getPageDefaultSize();
            }
            return null;
        }
        return Integer.parseInt(str);
    }
}
