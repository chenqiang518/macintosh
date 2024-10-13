package com.vernon.reaper.service.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.Thread.sleep;

public class RequestMockDemo1 {
    private static int port = 8089;
    @Test
    void requestMockTest() throws InterruptedException {
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(port));
        wireMockServer.start();
        configureFor(port);
        stubFor(get(urlEqualTo("/wiremock/resp"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "text/plain")
                                .withBody("Hello WireMock ResponseMath!")));

        // http://localhost:8089/__admin/ 可查看所有 请求的 request&response
        // 常见的响应缩写
        stubFor(get("/fine-with-body")
            .willReturn(ok("body content")));
        stubFor(get("/json")
                .willReturn(okJson("{ \"message\": \"Hello\"}")));
        // 重定向请求至接口 /new/place
        stubFor(get("/another")
                .willReturn(temporaryRedirect("/new/place")));
        stubFor(get("/new/place")
                .willReturn(okJson("{ \"message\": \"302 local\"}")));
        stubFor(get("/sorry-no")
                .willReturn(unauthorized()));
        // 仅返回一个状态码
        stubFor(get("/status-only")
                .willReturn(status(418)));


        //// 重定向 接口请求至新接口
        stubFor(get("/weather")
            .willReturn(temporaryRedirect("https://restapi.amap.com/v3/weather/weatherInfo?key=78b1c796c950ddc06d78d99e762e2c12&city=110000")));

        stubFor(get(urlPathEqualTo("/weatherMock"))
                .withQueryParam("city",equalTo("110114"))
                .willReturn(temporaryRedirect("https://restapi.amap.com/v3/weather/weatherInfo?key=78b1c796c950ddc06d78d99e762e2c12&city=110114"))
        );

        // 接口请求优先级 .atPriority(20) 数据越小优先级越高，对应的数据可以不连续
        stubFor(get(urlMatching("/api/.*")).atPriority(20)
                .willReturn(aResponse().withStatus(200).withBody("api demo")));
        stubFor(get(urlEqualTo("/api/resource")).atPriority(1)
                .willReturn(aResponse().withStatus(200).withBody("resource status")));
        stubFor(any(anyUrl()).atPriority(100)
                .willReturn(aResponse().withStatus(200).withBody("no matching")));

        sleep(1000000);
        reset();
        wireMockServer.stop();
    }
}
