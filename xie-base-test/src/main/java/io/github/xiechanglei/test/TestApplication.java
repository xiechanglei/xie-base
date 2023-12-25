package io.github.xiechanglei.test;

import io.github.xiechanglei.base.common.http.HttpHelper;
import io.github.xiechanglei.base.common.http.HttpResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) throws IOException {
        HttpResponse httpResponse = HttpHelper.buildBrowserClient().transfer(req ->
                req.cookie("MODE=0; PC_MODE=0; PAD_MODE=0; wangji-auth=wHVcdQmlxibeMDSwdMvHL8zURzn6uzza3tyS9NoApGlzS1Ni5laHl9FZRIcWvSg7bJcPkAUkRBvhwmdGvq2iDg==; THEME=default; X_PRODUCT=gu; X_GU_SID=XSS_mcSQKtC9LxgxF4BIpxdljpdKL8MbWM8giuhEbF5MV9S0Zzs; USER_ID=1; ACCOUNT=YWRtaW4=; USER_NAME=57O757uf566h55CG5ZGY")
        ).get("http://222.95.96.70:9090/api/v2/auth/menu/navmenu.js?1703139479829");
        String body = httpResponse.body();
        System.out.println(new String(body.getBytes("utf-8")));
    }
}
