package com.yuanfentiankon.controller;

import com.yuanfentiankon.model.entity.User;
import com.yuanfentiankon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 用户个人中心页
    @GetMapping("/profile")
    public String profile() {
        return "user/profile";
    }

    // 用户资料查看页
    @GetMapping("/view/{id}")
    public String viewUser(@org.springframework.web.bind.annotation.PathVariable Long id, Model model) {
        // 这里简单用userService查找用户，实际可根据业务调整
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "profile/profile";
    }
} 