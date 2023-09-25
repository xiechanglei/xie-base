package com.xiechanglei.code.base.common.json;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class JsonContainerAdapter implements JsonContainer {
    private DocumentContext documentContext;


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

}
