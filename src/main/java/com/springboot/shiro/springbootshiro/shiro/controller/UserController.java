package com.springboot.shiro.springbootshiro.shiro.controller;


import com.springboot.shiro.springbootshiro.shiro.entity.User;
import com.springboot.shiro.springbootshiro.shiro.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangzhilong
 * @since 2020-06-24
 */
@RestController
@RequestMapping("/shiro/user")
@Api(tags = "用户相关前端接口")
public class UserController {

    @Autowired
    IUserService userService;

    @RequiresUser
    @PostMapping("/addUser")
    @ApiOperation(value = "新增/注册用户")
    public String addUser(User user){
        boolean result = userService.save(user);
        if (result){
            return "新增成功";
        }else {
            return "新增失败";
        }
    }

    @RequiresPermissions("user-add")
    @GetMapping("/getUserPermission")
    @ApiOperation(value = "校验用户是否有user-add权限接口")
    public String getUserPermission(){
        return "user-add";
    }
}
