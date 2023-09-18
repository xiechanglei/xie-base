package com.xiechanglei.code.base.common.json;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TextJsonParser extends JsonParser {
    private final String body;

    public String body() {
        return body;
    }
}
