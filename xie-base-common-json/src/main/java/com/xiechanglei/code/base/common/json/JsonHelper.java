package com.xiechanglei.code.base.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) throws JsonProcessingException {
        return objectMapper.readValue(json, typeReference);
    }
}
