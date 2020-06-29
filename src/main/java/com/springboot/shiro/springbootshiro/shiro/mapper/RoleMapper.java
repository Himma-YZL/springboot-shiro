package com.springboot.shiro.springbootshiro.shiro.mapper;

import com.springboot.shiro.springbootshiro.shiro.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangzhilong
 * @since 2020-06-24
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from role where id in (SELECT role_id from user_role where user_id = #{userId})")
    List<Role> getRolesByUserId(Long userId);
}
