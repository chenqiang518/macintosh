package com.vernon.poppy.service;

import com.vernon.poppy.dto.HogwartsTestUserDTO;
import com.vernon.poppy.dto.UserDTO;
import com.vernon.poppy.entity.HogwartsTestUser;
import com.vernon.poppy.util.R;

public interface User2Service {

    // 用户登录
    R login(UserDTO userDTO);
    //用户注册
    String registerAndParam( UserDTO userDTO,  String module,
                             String desc,  String age);


    R register(HogwartsTestUser hogwartsTestUser);

    R find(HogwartsTestUser hogwartsTestUser);

    R update(HogwartsTestUserDTO hogwartsTestUser);

    R delete(HogwartsTestUser hogwartsTestUser);

}
