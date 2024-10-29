package com.vernon.poppy.service.source;

import com.vernon.poppy.dto.AccessTokenDTO;
import com.vernon.poppy.dto.DepartmentDTO;

import java.util.stream.Stream;

public class QYWeChatSource {
    private static final int id = (int) (Math.random()*1000000);

    public static Stream<AccessTokenDTO> getAccessToken() {
        AccessTokenDTO accessTokenDTO = AccessTokenDTO.getInstance();
        accessTokenDTO.setCorpid("wwf69470c1623ad868");
        accessTokenDTO.setCorpsecret("sn0VAZ4wxM0ELk0PYXgbS1QUpVXtTLnTd1N_ib5wTO4");
        return Stream.of(accessTokenDTO);

    }

    public static Stream<DepartmentDTO> departmentTest(){
        return Stream.of(new DepartmentDTO(){{
            this.setName("VERNON_"+id+"_部门自动化测试");
            this.setNameEn("VERNON_"+id);
            this.setParentId(1);
            this.setOrder(1);
            this.setId(id);
            this.setPathToSimpleList("schema/simple_list.json");
        }});
    }
}
