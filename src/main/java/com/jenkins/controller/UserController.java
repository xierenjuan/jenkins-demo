package com.jenkins.controller;

import com.jenkins.entity.User;
import com.jenkins.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户相关")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("测试接口")
    @GetMapping("t")
    public Object test2(Long id){
        User user = userService.select(id);
        return user;
    }
}
