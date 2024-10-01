package com.vernon.poppy.controller;

import com.vernon.poppy.dto.UserDTO;
import com.vernon.poppy.dto.UserXmlDTO;
import com.vernon.poppy.service.IUserService;
import com.vernon.poppy.service.UserService;
import com.vernon.poppy.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ceshiren")
public class BasePostController {

    @Autowired
    @Qualifier("userp")
//    UserService userp;
    UserService userService;

    //UserService userService= new UserServiceImpl();
    @Autowired(required=false)
//    UserService userp;
    IUserService IuserService;



    @PostMapping(value = "/login",produces = "application/json")
    String login(@RequestBody UserDTO userDTO){


//        String login(String username,String password){
        if(userDTO.getUsername().equals("leiming") && userDTO.getPassword().equals("vernon.com")){
            return "用户登录成功！用户名：{"+userDTO.getUsername()+"}，密码为：{"+userDTO.getPassword()+"}";

        }else {
            return "用户登录失败！用户名：{"+userDTO.getUsername()+"}，密码为：{"+userDTO.getPassword()+"}";

        }


//        return userp.login(userDto);

//        return userService.login(userDTO);
    }
//http://localhost:8080/{module}/register?desc={desc}&age={age}

    @PostMapping(value = "/{module}/register",produces = "application/json")
    @ApiOperation("post请求参数说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "module", value = "模块名称"),
            @ApiImplicitParam(name = "desc",value = "解释说明"),
            @ApiImplicitParam(name = "age",value = "年龄")
    })
    String registerAndParam(@RequestBody UserDTO userDto, @PathVariable String module,
                            @RequestParam String desc,@RequestParam String age){
        return userDto.getUsername()+"注册成功！密码是："+userDto.getPassword()+"\n注册模块为："+module
                +"\n对应年龄："+age+"\n其它描述为："+desc;
    }

    @PostMapping(value = "/{module}/registerxml",produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    String registerWithXMLAndParam(@RequestBody UserXmlDTO userXmlDTO, @PathVariable String module,
                                   @RequestParam String desc, @RequestParam String age){
        return userXmlDTO.getUname()+"注册成功！密码是："+userXmlDTO.getPwd()+"\n注册模块为："+module
                +"\n对应年龄："+age+"\n其它描述为："+desc;
    }

}

