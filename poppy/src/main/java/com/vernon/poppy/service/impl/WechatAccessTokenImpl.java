package com.vernon.poppy.service.impl;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.vernon.poppy.converter.WechatAccessTokenConverter;
import com.vernon.poppy.dao.WechatAccessTokenMapper;
import com.vernon.poppy.dto.AccessTokenDTO;
import com.vernon.poppy.entity.WechatAccessToken;
import com.vernon.poppy.service.WechatAccessTokenService;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.vernon.poppy.constant.WechatConstant.GET_TOKEN_URI;
import static com.vernon.poppy.constant.WechatConstant.WECHAT_REMOTE;
import static io.restassured.RestAssured.given;

@Service
public class WechatAccessTokenImpl implements WechatAccessTokenService {

    @Autowired
    WechatAccessTokenMapper wechatAccessTokenMapper;

    @Autowired
    WechatAccessTokenConverter wechatAccessTokenConverter;

    @Override
    public DocumentContext getAccessToken(AccessTokenDTO accessTokenDTO) {

        HashMap<String, Object> params = new HashMap<>() {{
            put("corpid", accessTokenDTO.getCorpid());
            put("corpsecret", accessTokenDTO.getCorpsecret());
        }};
        Response response = given()
                    .log().all()
                    .queryParams(params)
                .when()
                    .get(WECHAT_REMOTE + GET_TOKEN_URI)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

        return JsonPath.parse(response.getBody().asString());
    }

    @Override
    public WechatAccessToken getRefreshToken(AccessTokenDTO accessTokenDTO) {
        Example example = new Example(WechatAccessToken.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("corpId",accessTokenDTO.getCorpid())
                .andEqualTo("corpSecret",accessTokenDTO.getCorpsecret());

        List<WechatAccessToken> accessTokens = wechatAccessTokenMapper.selectByExample(example);

        WechatAccessToken wechatAccessToken;
        if (accessTokens.isEmpty()) {
            wechatAccessToken =  wechatAccessTokenConverter.AccessTokenDTOFor(accessTokenDTO);
            DocumentContext context = getAccessToken(accessTokenDTO);
            String accessToken = context.read("$.access_token");
            Integer expires = context.read("$.expires_in");
            Date expireIn = new Date(System.currentTimeMillis() + expires*1000);

            wechatAccessToken.setAccessToken(accessToken);
            wechatAccessToken.setDeleted((byte) 0);
            wechatAccessToken.setExpiresIn(expireIn);
            wechatAccessToken.setCreateTime(new Date());
            wechatAccessToken.setUpdateTime(new Date());
            wechatAccessTokenMapper.insert(wechatAccessToken);

        }else {
            wechatAccessToken =accessTokens.get(0);
            if (wechatAccessToken.getExpiresIn().before(new Date())){
                DocumentContext context = getAccessToken(accessTokenDTO);
                String accessToken = context.read("$.access_token");
                Integer expires = context.read("$.expires_in");
                Date expiresIn = new Date(System.currentTimeMillis() + expires*1000);
                wechatAccessToken.setAccessToken(accessToken);
                wechatAccessToken.setExpiresIn(expiresIn);
                wechatAccessToken.setUpdateTime(new Date());
                wechatAccessTokenMapper.updateByPrimaryKey(wechatAccessToken);

            }
        }
    return wechatAccessToken;
    }


    public static void main(String[] args) {
        AccessTokenDTO accessTokenDTO = AccessTokenDTO.getInstance();
        accessTokenDTO.setCorpid("wwf69470c1623ad868");
        accessTokenDTO.setCorpsecret("sn0VAZ4wxM0ELk0PYXgbS1QUpVXtTLnTd1N_ib5wTO4");
        WechatAccessTokenService wechatAccessTokenService = new WechatAccessTokenImpl();
        WechatAccessToken refreshToken = wechatAccessTokenService.getRefreshToken(accessTokenDTO);
        System.out.println(refreshToken.getAccessToken());
    }

}
