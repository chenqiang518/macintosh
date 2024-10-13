package com.vernon.reaper.service.restassured;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class WeatherTest {

    @Test
    void testWeather() {
        given().log().all()
                .queryParam("key", "78b1c796c950ddc06d78d99e762e2c12")
                .queryParam("city","110100")
                .when()
                .get("https://restapi.amap.com/v3/weather/weatherInfo")
                .then().log().all();
    }

    @Test
    void testPoxyWeather() {
        given().log().all()
                .queryParam("key", "78b1c796c950ddc06d78d99e762e2c12")
                .queryParam("city","110107")
                .when()
                // 需要请求对应 IP:Port
                .get("http://localhost:8089/v3/weather/weatherInfo")
                .then().log().all();
    }
}
