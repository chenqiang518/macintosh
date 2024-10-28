package com.vernon.poppy.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.vernon.poppy.service.BaseApiService;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import static com.vernon.poppy.util.Log.log;
import static io.qameta.allure.Allure.addAttachment;

public abstract class ApiFilter implements Filter {

    public Header header;

    public Cookie cookie;

    public String env;

    public ApiFilter(BaseApiService baseApiService) {
        this.header = baseApiService.getHeader();
        this.cookie = baseApiService.getCookie();
        this.env = baseApiService.getEnv();
    }



    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {

        return commonFilter(requestSpec, responseSpec, ctx);
    }

    public Response commonFilter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx){

        // 设置请求基地址 等于配置域名或IP
        requestSpec.baseUri(getUriByEnv(env));

        if (header != null) {
            // 添加请求头信息
            requestSpec.header(header);
        }

        if (cookie != null) {
            // 添加 Cookies
            requestSpec.cookie(cookie);
        }

        // 日志的配置: 使每个接口在调用过程中，自动打印输出日志信息
        // 1. 获取请求信息
        String requestsInfo = requestSpec.getMethod() + " "
                + requestSpec.getURI()
                + "\n Request Body: \n"
                + requestSpec.getBody();
        // 2. 发起真是请求，获取响应对象
        Response response = ctx.next(requestSpec, responseSpec);
        // 3. 获取响应信息
        String responseInfo = "\n Response Status: "
                + response.getStatusCode() + " "
                + response.getStatusLine()
                + "\n Response Body: "
                + response.getBody().asPrettyString();

        log.debug("接口请求: {}",requestsInfo);
        log.debug("接口响应: {}",responseInfo);
        // 将接口请求和响应日志添加至 allure 报告
        addAttachment("接口请求: ",requestsInfo);
        addAttachment("接口响应: ",responseInfo);

        return response;
    }

    // 获取对应环境 uri
    private String getUriByEnv(String env){
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        File yamlfile = new File(Objects.requireNonNull(contextClassLoader.getResource("config/envs.yaml")).getFile());
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<>() {};

        try {
            HashMap<String, String> envs = yamlMapper.readValue(yamlfile, typeRef);
            return envs.get(env);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
