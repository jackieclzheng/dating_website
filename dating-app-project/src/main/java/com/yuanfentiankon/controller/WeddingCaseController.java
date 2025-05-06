package com.yuanfentiankon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wedding-cases")
public class WeddingCaseController {
    
    @GetMapping("")
    public String listCases(Model model) {
        // TODO: 添加婚礼案例列表逻辑
        model.addAttribute("activeTab", "cases");
        return "weddingcases";  // 对应 weddingcases.html
    }
    
    // @GetMapping("/{id}")
    // public String showCase(@PathVariable Long id, Model model) {
    //     // TODO: 添加查看具体婚礼案例的逻辑
    //     return "weddingcase-detail";
    // }
}