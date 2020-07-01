package com.springboot.shiro.springbootshiro.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.springbootshiro.shiro.entity.User;
import com.springboot.shiro.springbootshiro.util.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Api(tags = "登录接口")
public class LoginController {

    @Value("${redis.host}")
    private String host;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public JSONObject login(String userName , String password){
        log.info("------------------{}---------------------", host);
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,password);
//        String newSessionId = ShiroUtil.getSession().getId().toString();
////        String newToken = ShiroUtil.getSession().getAttribute("token").toString();
//        Object o = redisTemplate.opsForValue().get(userName);
//        if (o!=null){
//            String oldToken = (String) o;
//            if (oldToken.equals(newSessionId)){
//                String sessionId = ShiroUtil.getSession().getId().toString();
//                User userInfo = ShiroUtil.getUserInfo();
//                jsonObject.put("flag","SUCCESS");
//                jsonObject.put("status","200");
//                jsonObject.put("MSG","登录成功");
//                jsonObject.put("SESSIONID",sessionId);
//                jsonObject.put("USERINFO",userInfo);
//                return jsonObject;
//            }
//        }
        try {
//            String sessionId = ShiroUtil.getSession().getId().toString();
//            User userInfo = ShiroUtil.getUserInfo();
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException uae) {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","未知账户");
            return jsonObject;
        } catch (IncorrectCredentialsException ice) {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","密码不正确");
            return jsonObject;
        } catch (LockedAccountException lae) {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","账户已锁定");
            return jsonObject;
        } catch (ExcessiveAttemptsException eae) {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","用户名或密码错误次数过多");
            return jsonObject;
        } catch (AuthenticationException ae) {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","用户名或密码不正确");
            return jsonObject;
        }
        if (subject.isAuthenticated()){
            String sessionId = ShiroUtil.getSession().getId().toString();
            User userInfo = ShiroUtil.getUserInfo();
//            String token = ShiroUtil.getSession().getAttribute("token").toString();
            jsonObject.put("flag","SUCCESS");
            jsonObject.put("status","200");
            jsonObject.put("MSG","登录成功");
            jsonObject.put("SESSIONID",sessionId);
            jsonObject.put("USERINFO",userInfo);
            redisTemplate.opsForValue().set(userName,sessionId,60, TimeUnit.SECONDS);
            return jsonObject;
        }else {
            jsonObject.put("flag","ERROR");
            jsonObject.put("status","500");
            jsonObject.put("MSG","登录失败");
            return jsonObject;
        }
    }

    @PostMapping("/loginOut")
    @ApiOperation(value = "退出登录")
    public String loginOut(){
        ShiroUtil.logout();
        return "退出登录";
    }
}
