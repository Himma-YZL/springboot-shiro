package com.springboot.shiro.springbootshiro.shiro.service.impl;

import com.springboot.shiro.springbootshiro.shiro.entity.User;
import com.springboot.shiro.springbootshiro.shiro.mapper.UserMapper;
import com.springboot.shiro.springbootshiro.shiro.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
