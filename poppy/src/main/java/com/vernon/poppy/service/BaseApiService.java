package com.vernon.poppy.service;

import io.restassured.filter.Filter;
import lombok.Getter;
import lombok.Setter;

public class BaseApiService {

    @Setter
    @Getter
    public String token;
    public Filter filter;
    @Setter
    public String role;

}
