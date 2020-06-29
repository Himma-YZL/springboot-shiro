package com.springboot.shiro.springbootshiro.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplatesController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
