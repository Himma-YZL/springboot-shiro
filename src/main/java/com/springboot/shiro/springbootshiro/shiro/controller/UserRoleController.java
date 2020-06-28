package com.springboot.shiro.springbootshiro.shiro.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.shiro.springbootshiro.shiro.entity.Role;
import com.springboot.shiro.springbootshiro.shiro.entity.User;
import com.springboot.shiro.springbootshiro.shiro.entity.UserRole;
import com.springboot.shiro.springbootshiro.shiro.service.IRoleService;
import com.springboot.shiro.springbootshiro.shiro.service.IUserRoleService;
import com.springboot.shiro.springbootshiro.shiro.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/shiro/user-role")
@Api(tags = "用户角色关系前端接口")
public class UserRoleController {

    @Autowired
    IUserRoleService userRoleService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IUserService userService;

    @PostMapping("/addUserRole")
    @ApiOperation(value = "新增用户角色配置")
    public String addUserRole(String userName,String role){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role",role);
        Role roleEntity = roleService.getOne(queryWrapper);

        QueryWrapper queryUserWrapper = new QueryWrapper<>();
        queryUserWrapper.eq("user_name",userName);
        User user = userService.getOne(queryUserWrapper);

        if (roleEntity != null && user != null){
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleEntity.getId());
            userRole.setUserId(user.getId());
            boolean result = userRoleService.save(userRole);
            if (result){
                return "用户新增角色成功";
            }else {
                return "用户新增角色失败";
            }
        }else {
            return "新增角色权限配置失败";
        }
    }
}
