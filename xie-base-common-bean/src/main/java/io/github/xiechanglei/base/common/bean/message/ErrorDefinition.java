package io.github.xiechanglei.base.common.bean.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDefinition {
    private int code;
    private String message;

    public static ErrorDefinition of(int code, String message) {
        return new ErrorDefinition(code, message);
    }
}
