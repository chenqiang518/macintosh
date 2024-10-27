package com.vernon.poppy.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.vernon.poppy.dto.DepartmentDTO;
import com.vernon.poppy.service.WechatDepartmentService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import static com.vernon.poppy.constant.WechatConstant.*;
import static io.restassured.RestAssured.given;

@Service
public class WechatDepartmentImpl implements WechatDepartmentService {
    @Override
    public DocumentContext createDepartment(DepartmentDTO departmentDTO, String accessToken) {

        JSONObject create = new JSONObject() {{
            put("name", departmentDTO.getName());
            put("name_en", departmentDTO.getNameEn());
            put("parentid", departmentDTO.getParentId());
            put("order", departmentDTO.getOrder());
            put("id", departmentDTO.getId());
        }};
        Response response = given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(create.toString())
                    .queryParam("access_token", accessToken)
                .when()
                    .post(WECHAT_REMOTE + CREATE_URI)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();
        return JsonPath.parse(response.getBody().asString());
    }

    @Override
    public DocumentContext getSimpleList(DepartmentDTO departmentDTO, String accessToken) {
        JSONObject params=new JSONObject(){{
            put("access_token", accessToken);
            put("id", departmentDTO.getParentId());
        }};
        Response response = given()
                    .log().all()
                    .queryParams(params)
                .when()
                    .get(WECHAT_REMOTE + SIMPLE_LIST_URI)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();
        return JsonPath.parse(response.getBody().asString());
    }

    @Override
    public DocumentContext delDepartment(DepartmentDTO departmentDTO, String accessToken) {
        JSONObject params = new JSONObject() {{
            put("id", departmentDTO.getId());
            put("access_token", accessToken);
        }};

        Response response = given()
                    .log().all()
                    .queryParams(params)
                .when()
                    .get(WECHAT_REMOTE + DELETE_URI)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();
        return JsonPath.parse(response.getBody().asString());
    }
}
