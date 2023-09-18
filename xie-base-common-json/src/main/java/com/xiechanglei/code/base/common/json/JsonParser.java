package com.xiechanglei.code.base.common.json;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public abstract class JsonParser {
    private DocumentContext documentContext;

    public abstract String body();


    /**
     * to Object with path
     * convertToJson(User.class, "data.user")
     */
    public <T> T readJson(String path, Class<T> clazz) {
        if (this.documentContext == null) {
            this.documentContext = JsonPath.parse(body());
        }
        return documentContext.read(path, clazz);
    }

    /**
     * toMap
     */
    public Map readJson() {
        return this.readJson("$", Map.class);
    }

    /**
     * to Object
     */
    public <T> T readJson(Class<T> clazz) {
        return this.readJson("$", clazz);
    }

}
