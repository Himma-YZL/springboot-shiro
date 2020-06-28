package com.springboot.shiro.springbootshiro.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.shiro.springbootshiro.shiro.entity.User;

/**
 * <p>
 *  登录 服务类
 * </p>
 *
 * @author yangzhilong
 * @since 2020-06-24
 */
public interface ILoginService {

    /**
     *
     */
    String loginUser(User user);
}
