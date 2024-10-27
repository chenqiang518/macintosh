package com.vernon.poppy.converter;

import com.vernon.poppy.dto.AccessTokenDTO;
import com.vernon.poppy.entity.WechatAccessToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WechatAccessTokenConverter {

    @Mappings({
            @Mapping(target = "corpId", source = "corpid"),
            @Mapping(target = "corpSecret", source = "corpsecret")
    })
    WechatAccessToken AccessTokenDTOFor(AccessTokenDTO accessTokenDTO);
}
