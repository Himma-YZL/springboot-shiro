package com.springboot.shiro.springbootshiro.shiro.service;

import com.springboot.shiro.springbootshiro.shiro.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangzhilong
 * @since 2020-06-24
 */
public interface IPermissionService extends IService<Permission> {

    List<Permission> getPermissionByRoleId(Long roleId);
}
