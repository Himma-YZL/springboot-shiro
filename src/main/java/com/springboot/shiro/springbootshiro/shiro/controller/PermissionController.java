package com.springboot.shiro.springbootshiro.shiro.controller;


import com.springboot.shiro.springbootshiro.shiro.entity.Permission;
import com.springboot.shiro.springbootshiro.shiro.service.IPermissionService;
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
@RequestMapping("/shiro/permission")
@Api(tags = "权限前端接口")
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @PostMapping("/addPermission")
    @ApiOperation(value = "新增权限")
    public String addPermission(Permission permission){
        boolean result = permissionService.save(permission);
        if (result){
            return "新增权限成功";
        }else {
            return "新增权限失败";
        }
    }

}
