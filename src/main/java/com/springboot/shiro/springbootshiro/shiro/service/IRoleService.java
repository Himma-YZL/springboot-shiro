package com.springboot.shiro.springbootshiro.shiro.service;

import com.springboot.shiro.springbootshiro.shiro.entity.Role;
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
public interface IRoleService extends IService<Role> {

    List<Role> getRolesByUserId(Long userId);
}
