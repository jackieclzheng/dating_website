package com.yuanfentiankon.controller;

import com.yuanfentiankon.model.entity.User;
import com.yuanfentiankon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

//@Controller
//@RequestMapping("/match")
//public class MatchController {
//    @Autowired
//    private UserService userService;
//
//    // 支持 /match?mode=card 和 /match?mode=list
//    @GetMapping("")
//    public String matchPage(@RequestParam(value = "mode", defaultValue = "card") String mode,
//                            Model model,
//                            Principal principal) {
//        // 获取当前用户（如未登录可用假数据）
//        List<Map<String, Object>> matches = new ArrayList<>();
//        Map<String, Object> user1 = new HashMap<>();
//        user1.put("user", Map.of(
//            "id", 1L,
//            "username", "小明",
//            "avatar", "/images/avatar1.png",
//            "age", 25,
//            "height", 175,
//            "location", "杭州",
//            "bio", "热爱运动，喜欢旅行，期待遇见有趣的你~",
//            "tags", List.of(
//                Map.of("tag", Map.of("name", "运动达人", "icon", "fas fa-basketball-ball")),
//                Map.of("tag", Map.of("name", "旅行", "icon", "fas fa-plane"))
//            )
//        ));
//        user1.put("score", 0.92);
//        Map<String, Object> user2 = new HashMap<>();
//        user2.put("user", Map.of(
//            "id", 2L,
//            "username", "小红",
//            "avatar", "/images/avatar2.png",
//            "age", 23,
//            "height", 165,
//            "location", "上海",
//            "bio", "猫系女孩，喜欢安静看书，也爱美食。",
//            "tags", List.of(
//                Map.of("tag", Map.of("name", "美食", "icon", "fas fa-utensils")),
//                Map.of("tag", Map.of("name", "文艺", "icon", "fas fa-book"))
//            )
//        ));
//        user2.put("score", 0.88);
//        Map<String, Object> user3 = new HashMap<>();
//        user3.put("user", Map.of(
//            "id", 3L,
//            "username", "小刚",
//            "avatar", "/images/avatar3.png",
//            "age", 28,
//            "height", 180,
//            "location", "北京",
//            "bio", "IT男，喜欢编程和健身，偶尔弹吉他。",
//            "tags", List.of(
//                Map.of("tag", Map.of("name", "程序员", "icon", "fas fa-laptop-code")),
//                Map.of("tag", Map.of("name", "健身", "icon", "fas fa-dumbbell"))
//            )
//        ));
//        user3.put("score", 0.85);
//        matches.add(user1);
//        matches.add(user2);
//        matches.add(user3);
//        model.addAttribute("matches", matches);
//        model.addAttribute("mode", mode);
//        model.addAttribute("activeTab", "match");
//        return "index";
//    }
//
//}

//@Controller
//@RequestMapping("/match")
//public class MatchController {
//    @Autowired
//    private UserService userService;
//
//    // 支持 /match?mode=card 和 /match?mode=list
//    @GetMapping("")
//    public String matchPage(@RequestParam(value = "mode", defaultValue = "card") String mode,
//                            Model model,
//                            Principal principal) {
//        // 准备匹配数据（保持您的现有代码不变）
//        List<Map<String, Object>> matches = new ArrayList<>();
//        Map<String, Object> user1 = new HashMap<>();
//        user1.put("user", Map.of(
//                "id", 1L,
//                "username", "小明",
//                "avatar", "/images/avatar1.png",
//                "age", 25,
//                "height", 175,
//                "location", "杭州",
//                "bio", "热爱运动，喜欢旅行，期待遇见有趣的你~",
//                "tags", List.of(
//                        Map.of("tag", Map.of("name", "运动达人", "icon", "fas fa-basketball-ball")),
//                        Map.of("tag", Map.of("name", "旅行", "icon", "fas fa-plane"))
//                )
//        ));
//        user1.put("score", 0.92);
//        // 保持其他用户数据不变
//        Map<String, Object> user2 = new HashMap<>();
//        // 在这里添加user2的数据...
//
//        Map<String, Object> user3 = new HashMap<>();
//        // 在这里添加user3的数据...
//
//        matches.add(user1);
//        matches.add(user2);
//        matches.add(user3);
//
//        // 添加到模型中
//        model.addAttribute("matches", matches);
//        model.addAttribute("activeTab", "match");
//
//        // 根据模式返回不同的视图
//        if ("list".equals(mode)) {
//            return "list";  // 返回templates/list.html
//        } else {
//            return "card";  // 返回templates/card.html（默认）
//        }
//    }
//}

@Controller
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private UserService userService;

    // 支持 /match?mode=card 和 /match?mode=list
    @GetMapping("")
    public String matchPage(@RequestParam(value = "mode", defaultValue = "card") String mode,
                            Model model,
                            Principal principal) {
        if ("card".equals(mode)) {
            // 卡片模式只展示一个用户
            Map<String, Object> user = new HashMap<>();
            user.put("avatarUrl", "/images/avatar1.png");
            user.put("nickname", "小明");
            user.put("age", 25);
            user.put("gender", "男");
            user.put("location", "杭州");
            user.put("education", "本科");
            user.put("occupation", "工程师");
            user.put("tags", Arrays.asList("运动达人", "旅行爱好者"));
            user.put("bio", "热爱运动，喜欢旅行，期待遇见有趣的你~");

            model.addAttribute("user", user);
            return "card";
        } else {
            // 列表模式展示多个用户
            List<Map<String, Object>> users = new ArrayList<>();

            Map<String, Object> user1 = new HashMap<>();
            user1.put("avatarUrl", "/images/avatar1.png");
            user1.put("nickname", "小明");
            user1.put("age", 25);
            user1.put("gender", "男");
            user1.put("location", "杭州");
            user1.put("education", "本科");
            user1.put("tags", Arrays.asList("运动达人", "旅行爱好者"));

            Map<String, Object> user2 = new HashMap<>();
            user2.put("avatarUrl", "/images/avatar2.png");
            user2.put("nickname", "小红");
            user2.put("age", 23);
            user2.put("gender", "女");
            user2.put("location", "上海");
            user2.put("education", "硕士");
            user2.put("tags", Arrays.asList("美食", "文艺"));

            Map<String, Object> user3 = new HashMap<>();
            user3.put("avatarUrl", "/images/avatar3.png");
            user3.put("nickname", "小刚");
            user3.put("age", 28);
            user3.put("gender", "男");
            user3.put("location", "北京");
            user3.put("education", "博士");
            user3.put("tags", Arrays.asList("程序员", "健身"));

            users.add(user1);
            users.add(user2);
            users.add(user3);

            model.addAttribute("users", users);
            model.addAttribute("currentPage", 1);
            model.addAttribute("totalPages", 5);

            return "list";
        }
    }
}
