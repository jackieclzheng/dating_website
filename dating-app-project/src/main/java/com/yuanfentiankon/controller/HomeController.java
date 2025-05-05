package com.yuanfentiankon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 首页跳转到登录页
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 登录页
    @GetMapping("/login")
    public String login() {
        return "login";
    }
} 