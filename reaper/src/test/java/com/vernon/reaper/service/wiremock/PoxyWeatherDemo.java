package com.vernon.reaper.service.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.Thread.sleep;

public class PoxyWeatherDemo {
    @Test
    void poxyWeatherMock() throws InterruptedException {
        int port = 8089;
        // 声明 WireMockServer 服务 并指定端口 8089
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(port)
                // 对接口返回数据进行Mock重写
                .extensions(new ResponseTransformer() {
                    @Override
                    public Response transform(Request request, Response response, FileSource files, Parameters parameters) {

                        return Response.Builder.like(response)
                                .body(
                                        response
                                                .getBodyAsString()
                                                .replace("weather","mockJava weather"))
                                .build();
                    }

                    @Override
                    public String getName() {
                        return "ResponseTransformerDemo";
                    }
                })

        );
        // 启动 wireMockServer
        wireMockServer.start();
        // 配置开放端口号 8089 与声明代理端口必须一致
        configureFor(port);

        stubFor(get(urlMatching(".*"))
            .willReturn(aResponse().proxiedFrom("https://restapi.amap.com"))
        );

        sleep(1000000);
        // 关闭服务之前 重置释放声明时使用的端口 8089, url, 请求参数 及响应 等资源 为关停服务准备
        WireMock.reset();
        // 关停代理服务
        wireMockServer.stop();
    }
}
