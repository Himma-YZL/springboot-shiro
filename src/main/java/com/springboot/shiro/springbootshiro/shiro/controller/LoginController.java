package com.springboot.shiro.springbootshiro.shiro.controller;

import com.springboot.shiro.springbootshiro.util.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "登录接口")
public class LoginController {

    @Value("${redis.host}")
    private String host;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public String login(String userName , String password){
        log.info("------------------{}---------------------", host);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()){
            return "登录成功";
        }else {
            return "登录失败";
        }
    }

    @PostMapping("/loginOut")
    @ApiOperation(value = "退出登录")
    public String loginOut(){
        ShiroUtil.logout();
        return "退出登录";
    }
}
