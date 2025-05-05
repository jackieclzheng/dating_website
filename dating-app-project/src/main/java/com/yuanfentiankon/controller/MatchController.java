package com.yuanfentiankon.controller;

import com.yuanfentiankon.model.entity.User;
import com.yuanfentiankon.model.entity.Match;
import com.yuanfentiankon.service.MatchService;
import com.yuanfentiankon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    // 匹配推荐页面
    @GetMapping("/recommend")
    public String recommend(Model model, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        List<User> candidates = matchService.recommendCandidates(currentUser.getId());
        model.addAttribute("candidates", candidates);
        return "match/recommend";
    }

    // 查看某个用户的详细资料
    @GetMapping("/profile/{userId}")
    public String viewProfile(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "match/profile";
    }

    // 喜欢某人
    @PostMapping("/like")
    public String like(@RequestParam Long targetUserId, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        matchService.likeUser(currentUser.getId(), targetUserId);
        return "redirect:/match/recommend";
    }

    // 不喜欢/跳过
    @PostMapping("/skip")
    public String skip(@RequestParam Long targetUserId, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        matchService.skipUser(currentUser.getId(), targetUserId);
        return "redirect:/match/recommend";
    }

    // 匹配成功列表
    @GetMapping("/success")
    public String matchSuccess(Model model, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        List<Match> matches = matchService.findMatchesByUserId(currentUser.getId());
        model.addAttribute("matches", matches);
        return "match/success";
    }
} 