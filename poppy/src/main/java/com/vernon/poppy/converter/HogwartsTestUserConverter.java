package com.vernon.poppy.converter;

import com.vernon.poppy.dto.HogwartsTestUserDTO;
import com.vernon.poppy.entity.HogwartsTestUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface HogwartsTestUserConverter {
    // 转换类
    //    hogwartsTestUserDTO 参数转换至  HogwartsTestUser
    @Mappings({
            @Mapping(target = "userName", source = "userName"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "email", source = "email")
    })
    HogwartsTestUser userDTOForUser(HogwartsTestUserDTO hogwartsTestUserDTO);

}
