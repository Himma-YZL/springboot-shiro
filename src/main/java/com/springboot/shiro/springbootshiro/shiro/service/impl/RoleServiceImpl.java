package com.springboot.shiro.springbootshiro.shiro.service.impl;

import com.springboot.shiro.springbootshiro.shiro.entity.Role;
import com.springboot.shiro.springbootshiro.shiro.mapper.RoleMapper;
import com.springboot.shiro.springbootshiro.shiro.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
