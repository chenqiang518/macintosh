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
public class WechatDepartmentImpl extends WechatDepartmentService {

    @Override
    public DocumentContext createDepartment(DepartmentDTO departmentDTO) {

        JSONObject create = new JSONObject() {{
            put("name", departmentDTO.getName());
            put("name_en", departmentDTO.getNameEn());
            put("parentid", departmentDTO.getParentId());
            put("order", departmentDTO.getOrder());
            put("id", departmentDTO.getId());
        }};
        Response response = given()
                    .filter(filter)
                    .contentType(ContentType.JSON)
                    .body(create.toString())
                    .queryParam("access_token", this.getToken())
                .when()
                    .post(CREATE_URI)
                .then()
                    .statusCode(200)
                    .extract().response();
        return JsonPath.parse(response.getBody().asString());
    }

    @Override
    public DocumentContext getSimpleList(DepartmentDTO departmentDTO) {
        JSONObject params=new JSONObject(){{
            put("access_token", token);
            put("id", departmentDTO.getParentId());
        }};
        Response response = given()
                    .filter(filter)
                    .queryParams(params)
                .when()
                    .get(SIMPLE_LIST_URI)
                .then()
                    .statusCode(200)
                    .extract().response();
        return JsonPath.parse(response.getBody().asString());
    }

    @Override
    public DocumentContext delDepartment(DepartmentDTO departmentDTO) {
        JSONObject params = new JSONObject() {{
            put("id", departmentDTO.getId());
            put("access_token", token);
        }};

        Response response = given()
                    .filter(filter)
                    .queryParams(params)
                .when()
                    .get(DELETE_URI)
                .then()
                    .statusCode(200)
                    .extract().response();
        return JsonPath.parse(response.getBody().asString());
    }
}
