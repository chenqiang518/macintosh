package com.vernon.poppy.service.impl;

import com.vernon.poppy.dto.UserDTO;
import com.vernon.poppy.service.UserService;
import org.springframework.stereotype.Service;

@Service("userp")
public class UserPServiceImpl implements UserService {

    @Override
    public String login(UserDTO userDTO) {
        System.out.println("userp");
        if(userDTO.getUsername().equals("hogwarts") && userDTO.getPassword().equals("ceshiren.com")){
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
