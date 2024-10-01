package com.vernon.poppy.service.impl;
import com.vernon.poppy.converter.HogwartsTestUserConverter;
import com.vernon.poppy.dao.HogwartsTestUserMapper;
import com.vernon.poppy.dto.HogwartsTestUserDTO;
import com.vernon.poppy.dto.UserDTO;
import com.vernon.poppy.entity.HogwartsTestUser;
import com.vernon.poppy.service.User2Service;
import com.vernon.poppy.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service("user2")
@Primary
public class User2ServiceImpl implements User2Service {

    @Autowired
    HogwartsTestUserMapper hogwartsTestUserMapper;

    @Autowired
    HogwartsTestUserConverter hogwartsTestUserConverter;

    @Override
    public R login(UserDTO userDTO) {
        if(userDTO.getUsername().equals("leiming") && userDTO.getPassword().equals("vernon.com")){
//            return "用户登录成功！用户名：{"+userDto.getUsername()+"}，密码为：{"+userDto.getPassword()+"}";
            return R.ok().data(userDTO).code(1000).message("登录成功");

//            return "用户登录成功！用户名：{"+userDto.getUsername()+"}，密码为：{"+userDto.getPassword()+"}";

        }else {
//            return "用户登录失败！用户名：{"+userDto.getUsername()+"}，密码为：{"+userDto.getPassword()+"}";
            return R.error().data(userDTO).code(50008).message("登录失败，密码错误");

        }

    }

    @Override
    public String registerAndParam(UserDTO userDTO, String module, String desc, String age) {
        return null;
    }

    @Override
    public R register(HogwartsTestUser hogwartsTestUser) {
        System.out.println(hogwartsTestUser);
        hogwartsTestUser.setCreateTime(new Date());
        hogwartsTestUser.setUpdateTime(new Date());

        int insertNum = hogwartsTestUserMapper.insert(hogwartsTestUser);
        System.out.println(insertNum);


        return R.ok().message(hogwartsTestUser.getUserName()+" 注册成功!!");
    }

    @Override
    public R find(HogwartsTestUser hogwartsTestUser) {
        HogwartsTestUser hogwartsTestUser1 = hogwartsTestUserMapper.selectOne(hogwartsTestUser);
        System.out.println(hogwartsTestUser1);
        return R.ok().data(hogwartsTestUser1).message("该用户存在");
    }

    @Override
    public R update(HogwartsTestUserDTO hogwartsTestUserDTO) {
//         hogwartsTestUserDTO---->  HogwartsTestUser

/*        String userName = hogwartsTestUserDTO.getUserName();
        String email = hogwartsTestUserDTO.getEmail();
        String password = hogwartsTestUserDTO.getPassword();
        HogwartsTestUser hogwartsTestUser = new HogwartsTestUser();
        hogwartsTestUser.setUserName(userName);
        hogwartsTestUser.setEmail(email);
        hogwartsTestUser.setPassword(password);*/
        //bean拷贝
        HogwartsTestUser hogwartsTestUser = hogwartsTestUserConverter.userDTOForUser(hogwartsTestUserDTO);
        //select * from user where username= and password=
        Example example = new Example(HogwartsTestUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",hogwartsTestUser.getUserName());

        List<HogwartsTestUser> hogwartsTestUsers = hogwartsTestUserMapper.selectByExample(example);
        System.out.println(hogwartsTestUsers.get(0));
        Long id = hogwartsTestUsers.get(0).getId();
        hogwartsTestUser.setId(id);
        hogwartsTestUser.setUpdateTime(new Date());
        int updateNum = hogwartsTestUserMapper.updateByPrimaryKeySelective(hogwartsTestUser);
        return R.ok().message("用户修改成功");
    }

    @Override
    public R delete(HogwartsTestUser hogwartsTestUser) {

        int delete = hogwartsTestUserMapper.delete(hogwartsTestUser);
        return R.ok().message("用户删除成功");
    }

}
