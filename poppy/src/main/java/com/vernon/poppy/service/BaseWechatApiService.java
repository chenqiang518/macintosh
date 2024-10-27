package com.vernon.poppy.service;

import com.jayway.jsonpath.DocumentContext;
import com.vernon.poppy.dto.AccessTokenDTO;
import com.vernon.poppy.entity.WechatAccessToken;

public abstract class BaseWechatApiService extends BaseApiService {

    public abstract void addWechatFilter();

    public abstract DocumentContext initWechatToken(AccessTokenDTO accessTokenDTO);

    public abstract void refreshWechatToken(AccessTokenDTO accessTokenDTO);
}
