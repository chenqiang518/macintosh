package com.vernon.poppy.controller;

import com.vernon.poppy.dto.HogwartsTestUserDTO;
import com.vernon.poppy.dto.UserDTO;
import com.vernon.poppy.entity.HogwartsTestUser;
import com.vernon.poppy.service.User2Service;
import com.vernon.poppy.service.UserService;
import com.vernon.poppy.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UserController {

    @Autowired
    User2Service user2;


    @PostMapping(value = "/login",produces = "application/json")
    R login(@RequestBody UserDTO userDTO){

        return user2.login(userDTO);
    }

    @PostMapping(value = "/reg",produces = "application/json")
    R registerUser(@RequestBody HogwartsTestUser hogwartsTestUser){

        return user2.register(hogwartsTestUser);
    }


    @GetMapping(value = "/find", produces = "application/json")
    R findUser(@RequestParam HogwartsTestUser hogwartsTestUser){

        return user2.find(hogwartsTestUser);
    }


    @PostMapping(value = "/update",produces = "application/json")
    R updateUser(@RequestBody HogwartsTestUserDTO hogwartsTestUserDTO){

        return user2.update(hogwartsTestUserDTO);
    }


    @PostMapping(value = "/delete",produces = "application/json")
    R deleteUser(@RequestBody HogwartsTestUser hogwartsTestUser){

        return user2.delete(hogwartsTestUser);
    }

}
