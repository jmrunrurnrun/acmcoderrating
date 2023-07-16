package com.buct.acmer.controller;

import com.buct.acmer.entity.PublicProperty;
import com.buct.acmer.entity.User;
import com.buct.acmer.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户信息")
@RestController
@RequestMapping("/acmer/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;


    @ApiOperation("登录用户")
    @PostMapping("/login")
    public PublicProperty login(@RequestBody User user){
        System.out.println("testuser1");
        User exist = userService.getById(user.getUserId());
        System.out.println("testuser");
        if(exist == null){//用户不存在
            return new PublicProperty(400,"id not exist",null);
        }else {//判断密码是否相等
            if( exist.getUserPassword().equals(user.getUserPassword()) ){
                return new PublicProperty(200,"success",null);
            }else{
                return new PublicProperty(400,"password wrong",null);
            }

        }
    }
    @ApiOperation("注册用户")
    @PostMapping("/insert")
    public PublicProperty insert(@RequestBody User user){

        User exist = userService.getById(user.getUserId());
        if(exist == null){
            boolean save = userService.save(user);
            if(save){
                return new PublicProperty(200,"success",null);
            }else{
                return new PublicProperty(400,"failed",null);
            }
        }else {//用户已存在
            return new PublicProperty(400,"user exist",null);
        }
    }

    @ApiOperation("修改密码")
    @PostMapping("/update")
    public PublicProperty<User> update(@RequestBody User user){

        User exist = userService.getById(user.getUserId());
        if(exist!=null){
            boolean update = userService.updateById(exist);
            if(update){
                return new PublicProperty(200,"success",user);
            }else{
                return new PublicProperty(400,"failed",null);
            }
        }else {
            return new PublicProperty(400,"user does not exist",null);
        }

    }

}
