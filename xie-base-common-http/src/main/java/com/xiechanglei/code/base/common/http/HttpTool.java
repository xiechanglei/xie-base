package com.xiechanglei.code.base.common.http;

import java.io.IOException;

public class HttpTool {
    public static HttpRequest build() {
        return new HttpRequest();
    }

    public static HttpRequest buildBrowserClient() {
        return new HttpRequest().transfer(trans -> trans.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36"));
    }

    public static HttpResponse get(String url) throws IOException {
        return build().get(url);
    }

    public static HttpResponse browserGet(String url) throws IOException {
        return buildBrowserClient().get(url);
    }
}
