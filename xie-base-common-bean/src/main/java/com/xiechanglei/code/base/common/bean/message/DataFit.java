package com.xiechanglei.code.base.common.bean.message;

import java.util.TreeMap;

public class DataFit extends TreeMap<String, Object> {
    public DataFit fit(String key, Object value) {
        if (value != null) {
            this.put(key, value);
        }
        return this;
    }

    public static DataFit of(String key, Object value) {
        return new DataFit().fit(key, value);
    }

    public String toUrl() {
        StringBuilder sb = new StringBuilder();
        this.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append("&");
        });
        return sb.substring(0, sb.length() - 1);
    }
}
