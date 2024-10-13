package com.vernon.reaper.service.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.Thread.sleep;

public class BasicMockDemo {
    private static int port = 8089;

    @Test
    void basicMockTest() throws InterruptedException {
        // 声明 WireMockServer 服务 并指定端口 8089
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(port));
        // 启动 wireMockServer
        wireMockServer.start();
        // 配置开放端口号 8089 与声明代理端口必须一致
        configureFor(port);
        // 做一些业务请求逻辑代码等等

        stubFor(get(urlEqualTo("/wiremock"))
                .willReturn(aResponse().withBody("this is a mock server"))
        );
        // withStatus(404) 指定接口返回状态
        stubFor(get(urlEqualTo("/wiremock1"))
                .willReturn(aResponse().withStatus(404).withBody("this is a mock server"))
        );

        sleep(1000000);

        // 关闭服务之前 重置释放声明时使用的端口 8089, url, 请求参数 及响应 等资源 为关停服务准备
        WireMock.reset();
        // 关停代理服务
        wireMockServer.stop();
    }
}
