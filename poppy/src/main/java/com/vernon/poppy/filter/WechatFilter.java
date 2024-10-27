package com.vernon.poppy.filter;

import com.vernon.poppy.service.BaseWechatApiService;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class WechatFilter extends ApiFilter {

    public String accessToken;

    public WechatFilter(BaseWechatApiService wechatApiService) {
        super(wechatApiService);
        this.accessToken = wechatApiService.getAccessToken();
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        // 添加公共请求参数
        requestSpec.queryParam("access_token",accessToken);

        return commonFilter(requestSpec, responseSpec, ctx);
    }
}
