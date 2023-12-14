package io.github.xiechanglei.base.common.http;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class HttpRequest {
    private final HttpDataTransfer httpDataTransfer = new HttpDataTransfer();

    /**
     * 塞数据
     */
    public HttpRequest transfer(HttpDataTransfer.HttpDataTransferProvider provider) {
        provider.opera(httpDataTransfer);
        return this;
    }

    //发请求获取内容
    public HttpResponse get(String url) throws IOException {
        return request(url, "GET");
    }

    public HttpResponse post(String url) throws IOException {
        return  request(url, "POST");
    }

    public HttpResponse request(String url, String method) throws IOException {
        return parse(buildConnection(url).method(Connection.Method.valueOf(method)).execute());
    }
    //下载东西

    private HttpResponse parse(Connection.Response response)  {
        return new HttpResponse(response);
    }

    private Connection buildConnection(String url)  {
        Connection connection = Jsoup
                .connect(url)
                .cookies(httpDataTransfer.getCookies())
                .headers(httpDataTransfer.getHeaders())
                .ignoreContentType(true);
        if (httpDataTransfer.isJson()) {
            connection.requestBody(httpDataTransfer.getBody());
        } else {
            connection.data(httpDataTransfer.getParams());
        }
        return connection;
    }
}
