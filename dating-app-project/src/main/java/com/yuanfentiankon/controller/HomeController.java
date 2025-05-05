package com.yuanfentiankon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.yuanfentiankon.model.entity.User;
import java.util.*;

@Controller
public class HomeController {
    // 首页跳转到登录页
    @GetMapping("/")
    public String home(Model model) {
        // 构造测试数据
        List<Map<String, Object>> matches = new ArrayList<>();
        Map<String, Object> user1 = new HashMap<>();
        user1.put("user", Map.of(
            "id", 1L,
            "username", "小明",
            "avatar", "/images/avatar1.png",
            "age", 25,
            "height", 175,
            "location", "杭州",
            "bio", "热爱运动，喜欢旅行，期待遇见有趣的你~",
            "tags", List.of(
                Map.of("tag", Map.of("name", "运动达人", "icon", "fas fa-basketball-ball")),
                Map.of("tag", Map.of("name", "旅行", "icon", "fas fa-plane"))
            )
        ));
        user1.put("score", 0.92);
        Map<String, Object> user2 = new HashMap<>();
        user2.put("user", Map.of(
            "id", 2L,
            "username", "小红",
            "avatar", "/images/avatar2.png",
            "age", 23,
            "height", 165,
            "location", "上海",
            "bio", "猫系女孩，喜欢安静看书，也爱美食。",
            "tags", List.of(
                Map.of("tag", Map.of("name", "美食", "icon", "fas fa-utensils")),
                Map.of("tag", Map.of("name", "文艺", "icon", "fas fa-book"))
            )
        ));
        user2.put("score", 0.88);
        Map<String, Object> user3 = new HashMap<>();
        user3.put("user", Map.of(
            "id", 3L,
            "username", "小刚",
            "avatar", "/images/avatar3.png",
            "age", 28,
            "height", 180,
            "location", "北京",
            "bio", "IT男，喜欢编程和健身，偶尔弹吉他。",
            "tags", List.of(
                Map.of("tag", Map.of("name", "程序员", "icon", "fas fa-laptop-code")),
                Map.of("tag", Map.of("name", "健身", "icon", "fas fa-dumbbell"))
            )
        ));
        user3.put("score", 0.85);
        matches.add(user1);
        matches.add(user2);
        matches.add(user3);
        model.addAttribute("matches", matches);
        return "index";
    }

    // 登录页
    @GetMapping("/login")
    public String login() {
        return "login";
    }
} 