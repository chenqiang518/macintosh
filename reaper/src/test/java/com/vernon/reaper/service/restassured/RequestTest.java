package com.vernon.reaper.service.restassured;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestTest {

    @Test
    void request() {
        given().log().all()
                .header("Accept", "xml")
                .cookie("session","12345")
                .queryParam("search_term","WireMock")
                .auth().preemptive().basic("cq18390522307@163.com", "hogwarts123")
                //.body("{a:1,b:99,c:20}")
        .when()
                .post("http://localhost:8089/wiremock")
                .then().log().all();
    }
}
