package com.vernon.poppy.filter;

import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class WechatFilter extends ApiFilter {

    public String token;

    public WechatFilter(String token,String uri) {
        super(uri);
        this.token = token;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        // 添加请求头信息
        requestSpec.header("access_token",token);
        return commonFilter(requestSpec, responseSpec, ctx);
    }
}
