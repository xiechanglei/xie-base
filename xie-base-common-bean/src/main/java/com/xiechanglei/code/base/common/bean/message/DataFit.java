package com.xiechanglei.code.base.common.bean.message;

import java.util.TreeMap;

/**
 * 数据组装类，通常用于构建一些动态的属性对象，如：
 * <pre>
 *     DataFit dataFit = DataFit.of("name", "xiechanglei").fit("age", 18);
 * </pre>
 * 也可以用来构建URL参数，如：
 * <pre>
 *     String url = DataFit.of("name", "xiechanglei").fit("age", 18).toUrl();
 *     // url = name=xiechanglei&age=18
 * </pre>
 * 此类是通过继承TreeMap来实现的，所以生成的url参数是有序的（按照key升序），因为很多时候都有这种需求，比如做数据签名等等
 */
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
