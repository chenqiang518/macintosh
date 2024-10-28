package com.vernon.poppy.service;

import com.vernon.poppy.dto.AccessTokenDTO;
import com.vernon.poppy.service.source.QYWeChatSource;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class WechatBaseTest extends BaseTest {

    @Autowired
    BaseWechatApiService baseWechatApiService;

    @BeforeAll
    void setUpAll() {
        // 多角色多权限鉴权，前置处理
        this.role="admin";

        Authorize(role);
    }

    private void Authorize(String role){
        baseWechatApiService.setRole(role);
        System.out.println( "role = " + role );
        AccessTokenDTO accessTokenDTO = QYWeChatSource.getAccessToken().collect(Collectors.toList()).get(0);
        baseWechatApiService.refreshWechatToken(accessTokenDTO);
    }

}
