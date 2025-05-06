package com.yuanfentiankon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("activeTab", "home");
        return "home";  // 对应 home.html
    }

    // @GetMapping("/login")
    // public String login() {
    //     return "login";
    // }

    // 替换原来的login方法为以下代码
    @GetMapping({"/login", "/login.html"})
    public String login(HttpServletRequest request) {
        System.out.println("请求URL: " + request.getRequestURL());
        System.out.println("请求URI: " + request.getRequestURI());
        System.out.println("请求方法: " + request.getMethod());
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}