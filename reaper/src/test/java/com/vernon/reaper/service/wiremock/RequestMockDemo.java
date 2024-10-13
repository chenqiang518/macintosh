package com.vernon.reaper.service.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.Thread.sleep;

public class RequestMockDemo {
    private static int port = 8089;
    @Test
    void requestMockTest() throws InterruptedException {
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(port));
        wireMockServer.start();
        configureFor(port);
        /**
         * header   "Accept", containing("xml")
         * cookie   "session", matching(".12345.")
         * QueryParam   "search_term", equalTo("WireMock")
         * BasicAuth
         * RequestBody  matchingJsonPath("$.a",equalTo("1"))
         */
        // 1. any() 表示希望匹配任何请求方法: post, get
        // 2. urlPathEqualTo("/wiremock") 代表 请求url可带参数 QueryParam
        stubFor(any(urlPathEqualTo("/wiremock"))
                // 匹配 header 参数
                .withHeader("Accept", equalTo("xml"))
                // 匹配 cookie 参数
                .withCookie("session", matching(".*12345.*"))
                // 匹配 url 请求参数
                .withQueryParam("search_term", equalTo("WireMock"))
                // 匹配登陆名密码
                .withBasicAuth("cq18390522307@163.com","hogwarts123")
                // 匹配请求体
                .withRequestBody(matchingJsonPath("$.a",equalTo("1")))
                // 返回
                .willReturn(aResponse().withStatus(200).withBody("request mock server!"))

        );

        sleep(1000000);
        reset();
        wireMockServer.stop();
    }
}
