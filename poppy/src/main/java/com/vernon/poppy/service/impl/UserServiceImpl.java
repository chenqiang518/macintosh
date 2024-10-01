package com.vernon.poppy.service.impl;

import com.vernon.poppy.dto.UserDTO;
import com.vernon.poppy.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("user")
@Primary
public class UserServiceImpl implements UserService {

    @Override
    public String  login(UserDTO userDTO) {
        if(userDTO.getUsername().equals("leiming") && userDTO.getPassword().equals("2597758")){
            return "用户登录成功！用户名：{"+userDTO.getUsername()+"}，密码为：{"+userDTO.getPassword()+"}";

        }else {
            return "用户登录失败！用户名：{"+userDTO.getUsername()+"}，密码为：{"+userDTO.getPassword()+"}";

        }

    }

    @Override
    public String registerAndParam(UserDTO userDTO, String module, String desc, String age) {
        return null;
    }

}
