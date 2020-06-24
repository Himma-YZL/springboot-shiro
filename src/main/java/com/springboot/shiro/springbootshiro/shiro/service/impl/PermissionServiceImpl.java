package com.springboot.shiro.springbootshiro.shiro.service.impl;

import com.springboot.shiro.springbootshiro.shiro.entity.Permission;
import com.springboot.shiro.springbootshiro.shiro.mapper.PermissionMapper;
import com.springboot.shiro.springbootshiro.shiro.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangzhilong
 * @since 2020-06-24
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
