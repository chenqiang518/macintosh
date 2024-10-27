package com.vernon.poppy.service;

import com.jayway.jsonpath.DocumentContext;
import com.vernon.poppy.dto.AccessTokenDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseWechatApiService extends BaseApiService {

    public String accessToken;
    public String role;

    public abstract void addAuthorization(BaseWechatApiService baseWechatApiService);

    public abstract DocumentContext initWechatToken(AccessTokenDTO accessTokenDTO);

    public abstract void refreshWechatToken(AccessTokenDTO accessTokenDTO);
}
