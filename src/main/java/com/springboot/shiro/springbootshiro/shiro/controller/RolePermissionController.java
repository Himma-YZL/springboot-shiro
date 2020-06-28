package com.springboot.shiro.springbootshiro.shiro.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.shiro.springbootshiro.shiro.entity.Permission;
import com.springboot.shiro.springbootshiro.shiro.entity.Role;
import com.springboot.shiro.springbootshiro.shiro.entity.RolePermission;
import com.springboot.shiro.springbootshiro.shiro.service.IPermissionService;
import com.springboot.shiro.springbootshiro.shiro.service.IRolePermissionService;
import com.springboot.shiro.springbootshiro.shiro.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangzhilong
 * @since 2020-06-24
 */
@RestController
@RequestMapping("/shiro/role-permission")
@Api(tags = "角色权限关系前端接口")
public class RolePermissionController {

    @Autowired
    IRolePermissionService rolePermissionService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IPermissionService permissionService;

    @PostMapping("/addUserRole")
    @ApiOperation(value = "新增角色权限配置")
    public String addUserRole(String role,String permission){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role",role);
        Role roleEntity = roleService.getOne(queryWrapper);

        QueryWrapper queryPermissionWrapper = new QueryWrapper<>();
        queryPermissionWrapper.eq("permission",permission);
        Permission permissionEntity = permissionService.getOne(queryPermissionWrapper);

        if (roleEntity != null && permissionEntity != null){
            RolePermission rolePermission =new RolePermission();
            rolePermission.setRoleId(roleEntity.getId());
            rolePermission.setPermissionId(permissionEntity.getId());

            boolean result = rolePermissionService.save(rolePermission);
            if (result){
                return "新增角色权限配置成功";
            }else {
                return "新增角色权限配置失败";
            }
        }else {
            return "新增角色权限配置失败";
        }

    }
}
