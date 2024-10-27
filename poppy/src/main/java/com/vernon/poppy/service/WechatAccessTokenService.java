package com.vernon.poppy.service;

import com.jayway.jsonpath.DocumentContext;
import com.vernon.poppy.dto.AccessTokenDTO;
import com.vernon.poppy.entity.WechatAccessToken;

public interface WechatAccessTokenService {

    DocumentContext getAccessToken(AccessTokenDTO accessTokenDTO);

    WechatAccessToken getRefreshToken(AccessTokenDTO accessTokenDTO);
}
