package com.springboot.shiro.springbootshiro.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.shiro.springbootshiro.shiro.entity.Permission;
import com.springboot.shiro.springbootshiro.shiro.entity.Role;
import com.springboot.shiro.springbootshiro.shiro.entity.User;
import com.springboot.shiro.springbootshiro.shiro.entity.UserRole;
import com.springboot.shiro.springbootshiro.shiro.service.IRolePermissionService;
import com.springboot.shiro.springbootshiro.shiro.service.IUserRoleService;
import com.springboot.shiro.springbootshiro.shiro.service.IUserService;
import com.springboot.shiro.springbootshiro.shiro.service.impl.PermissionServiceImpl;
import com.springboot.shiro.springbootshiro.shiro.service.impl.RoleServiceImpl;
import freemarker.template.utility.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PermissionServiceImpl permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //根据用户名去数据库查询用户信息
        if (StringUtils.isEmpty(userName) || "null".equals(userName.toLowerCase())){
            return simpleAuthorizationInfo;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        User user = userService.getOne(queryWrapper);
        List<Role> roleList = roleService.getRolesByUserId(user.getId());
        //添加角色和权限
        for (Role role : roleList){
            simpleAuthorizationInfo.addRole(role.getRole());
            List<Permission> permissionList = permissionService.getPermissionByRoleId(role.getId());
            for (Permission permission : permissionList){
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
//        String username = (String) SecurityUtils.getSubject().getPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        Set<String> stringSet = new HashSet<>();
//        stringSet.add("user:show");
//        stringSet.add("user:admin");
//        info.setStringPermissions(stringSet);
//        return info;
//        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        if (StringUtils.isEmpty(userName) || "null".equals(userName)){
            throw new AuthenticationException("用户名错误");
        }else if (StringUtils.isEmpty(password) || "null".equals(password)){
            throw new AuthenticationException("密码格式错误");
        }else {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_name",userName);
            User user = userService.getOne(queryWrapper);
            if (password.equals(user.getPassword())){
                return new SimpleAuthenticationInfo(userName, password,getName());
            }else {
                throw new AuthenticationException("密码错误");
            }
        }
    }
}