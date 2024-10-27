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

@Service("wechatDepartmentService")
public class WechatDepartmentImpl extends WechatDepartmentService {

    // 创建部门
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
                    // 添加公共部分过滤器
                    .filter(filter)
                    .contentType(ContentType.JSON)
                    .body(create.toString())
                .when()
                    .post(CREATE_URI)
                .then()
                    .statusCode(200)
                    .extract().response();
        return JsonPath.parse(response.getBody().asString());
    }

    // 获取子部门ID列表
    @Override
    public DocumentContext getSimpleList(DepartmentDTO departmentDTO) {
        Response response = given()
                    // 添加公共部分过滤器
                    .filter(filter)
                    .queryParam("id",departmentDTO.getParentId())
                .when()
                    .get(SIMPLE_LIST_URI)
                .then()
                    .statusCode(200)
                    .extract().response();
        return JsonPath.parse(response.getBody().asString());
    }

    // 删除部门
    @Override
    public DocumentContext delDepartment(DepartmentDTO departmentDTO) {

        Response response = given()
                    // 添加公共部分过滤器
                    .filter(filter)
                    .queryParam("id", departmentDTO.getId())
                .when()
                    .get(DELETE_URI)
                .then()
                    .statusCode(200)
                    .extract().response();
        return JsonPath.parse(response.getBody().asString());
    }
}
