package com.vernon.poppy.service;

import com.vernon.poppy.dto.UserDTO;

public interface UserService {

    // 用户登录
    String login(UserDTO userDTO);
    //用户注册
    String registerAndParam( UserDTO userDTO,  String module,
                             String desc,  String age);

}
