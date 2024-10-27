package com.vernon.poppy.service;

import io.restassured.filter.Filter;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import lombok.Getter;
import lombok.Setter;

public class BaseApiService {

    @Setter
    @Getter
    public Header header;
    @Getter
    public Cookie cookie;
    public Filter filter;
    @Getter
    @Setter
    public String baseUri;

}
