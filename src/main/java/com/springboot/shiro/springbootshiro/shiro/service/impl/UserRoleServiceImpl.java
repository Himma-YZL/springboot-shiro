package com.springboot.shiro.springbootshiro.shiro.service.impl;

import com.springboot.shiro.springbootshiro.shiro.entity.UserRole;
import com.springboot.shiro.springbootshiro.shiro.mapper.UserRoleMapper;
import com.springboot.shiro.springbootshiro.shiro.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
