package com.vernon.poppy.service;

import com.jayway.jsonpath.DocumentContext;
import com.vernon.poppy.PoppyApplication;
import com.vernon.poppy.dto.AccessTokenDTO;
import com.vernon.poppy.dto.DepartmentDTO;
import com.vernon.poppy.entity.WechatAccessToken;
import com.vernon.poppy.service.impl.WechatDepartmentImpl;
import com.vernon.poppy.service.source.QYWeChatSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PoppyApplication.class)
@ExtendWith(SpringExtension.class)
class WechatDepartmentTest {
    @Autowired
    WechatDepartmentService wechatDepartment;

    @Autowired
    WechatAccessTokenService wechatAccessToken;

    private WechatAccessToken accessToken;

    @BeforeEach
    void setUp() {
        wechatDepartment = new WechatDepartmentImpl();
        AccessTokenDTO accessTokenDTO = QYWeChatSource.getAccessToken().collect(Collectors.toList()).get(0);
        accessToken = wechatAccessToken.getRefreshToken(accessTokenDTO);
    }

    @ParameterizedTest
    @MethodSource("com.vernon.poppy.service.source.QYWeChatSource#departmentTest")
    @Order(1)
    void createDepartmentTest(DepartmentDTO departmentDTO) {
        DocumentContext department = wechatDepartment.createDepartment(departmentDTO, accessToken.getAccessToken());

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
    void getSimpleListTest(DepartmentDTO departmentDTO) {
        DocumentContext simpleList = wechatDepartment.getSimpleList(departmentDTO, accessToken.getAccessToken());
        int errcode = simpleList.read("$.errcode");
        String errmsg = simpleList.read("$.errmsg");
        List<Integer> ids = simpleList.read("$..id");
        List<Integer> parentIds = simpleList.read("$..parentid");
        List<Integer> orders = simpleList.read("$..order");

        assertAll(
                () -> assertThat(errcode,equalTo(0)),
                () -> assertThat(errmsg,equalTo("ok")),
                () -> assertThat(ids,hasItem(equalTo(departmentDTO.getId()))),
                () -> assertThat(parentIds,hasItem(equalTo(departmentDTO.getParentId()))),
                () -> assertThat(orders,hasItem(equalTo(departmentDTO.getOrder())))
        );
    }

    @ParameterizedTest
    @MethodSource("com.vernon.poppy.service.source.QYWeChatSource#departmentTest")
    @Order(3)
    void delDepartmentTest(DepartmentDTO departmentDTO) {

        DocumentContext delete = wechatDepartment.delDepartment(departmentDTO, accessToken.getAccessToken());
        int errcode = delete.read("$.errcode");
        String errmsg = delete.read("$.errmsg");

        assertAll(
                () -> assertThat(errcode,equalTo(0)),
                () -> assertThat(errmsg,equalTo("deleted"))
        );

        DocumentContext getContext = wechatDepartment.getSimpleList(departmentDTO, accessToken.getAccessToken());

        List<Integer> partyId = getContext.read("$..id");

        assertAll(
                () -> assertThat(partyId, not(hasItem(departmentDTO.getId())))
        );
    }
}