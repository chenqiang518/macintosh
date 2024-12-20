package com.vernon.poppy.service;

import com.jayway.jsonpath.DocumentContext;
import com.vernon.poppy.dto.DepartmentDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class WechatDepartmentTest extends WechatBaseTest {

    @Autowired
    WechatDepartmentService wechatDepartmentService;

    @BeforeAll
    void setUp() {
        baseWechatApiService.setEnv("default");
        wechatDepartmentService.addAuthorization(baseWechatApiService);
    }

    @ParameterizedTest
    @MethodSource("com.vernon.poppy.service.source.QYWeChatSource#departmentTest")
    @Order(1)
    @DisplayName("测试接口_创建部门")
    void createDepartmentTest(DepartmentDTO departmentDTO) {
        DocumentContext department = wechatDepartmentService.createDepartment(departmentDTO);

        int errcode = department.read("$.errcode");
        String errmsg = department.read("$.errmsg");
        Integer actualId = department.read("$.id");

        assertAll(
                () -> assertThat(errcode,equalTo(0)),
                () -> assertThat(errmsg,equalTo("created")),
                () -> assertThat(actualId,equalTo(departmentDTO.getId()))
        );

        getSimpleListTest(departmentDTO);

    }

    @ParameterizedTest
    @MethodSource("com.vernon.poppy.service.source.QYWeChatSource#departmentTest")
    @Order(2)
    @DisplayName("测试接口_获取子部门ID列表")
    void getSimpleListTest(DepartmentDTO departmentDTO) {
        DocumentContext simpleList = wechatDepartmentService.getSimpleList(departmentDTO);
        int errcode = simpleList.read("$.errcode");
        List<Integer> ids = simpleList.read("$..id");
        List<Integer> parentIds = simpleList.read("$..parentid");

        assertAll(
                () -> assertThat(errcode,equalTo(0)),
                () -> assertThat(ids,hasItem(equalTo(departmentDTO.getId()))),
                () -> assertThat(parentIds,hasItem(equalTo(departmentDTO.getParentId())))
        );
    }

    @ParameterizedTest
    @MethodSource("com.vernon.poppy.service.source.QYWeChatSource#departmentTest")
    @Order(3)
    @DisplayName("测试接口_删除部门")
    void delDepartmentTest(DepartmentDTO departmentDTO) {

        DocumentContext delete = wechatDepartmentService.delDepartment(departmentDTO);
        int errcode = delete.read("$.errcode");
        String errmsg = delete.read("$.errmsg");

        assertAll(
                () -> assertThat(errcode,equalTo(0)),
                () -> assertThat(errmsg,equalTo("deleted"))
        );

        DocumentContext getContext = wechatDepartmentService.getSimpleList(departmentDTO);

        List<Integer> partyId = getContext.read("$..id");

        assertAll(
                () -> assertThat(partyId, not(hasItem(departmentDTO.getId())))
        );
    }
}