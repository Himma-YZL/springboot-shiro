package com.springboot.shiro.springbootshiro.shiro.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 角色集合
     */
    private List<Role> roleList;

    /**
     * 权限集合
     */
    private List<Permission> permissionList;
}
