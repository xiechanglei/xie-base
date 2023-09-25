package com.xiechanglei.code.base.common.json;

import java.util.Map;

public interface JsonContainer {
    String body();

    <T> T readJson(String path, Class<T> clazz);
    /**
     * toMap
     */
    default Map readJson() {
        return this.readJson("$", Map.class);
    }

    /**
     * to Object
     */
    default <T> T readJson(Class<T> clazz) {
        return this.readJson("$", clazz);
    }


}
