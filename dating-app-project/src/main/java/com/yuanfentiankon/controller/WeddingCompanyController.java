package com.yuanfentiankon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wedding-companies")
public class WeddingCompanyController {
    
    @GetMapping("")
    public String listCompanies(Model model) {
        // TODO: 添加婚庆公司列表逻辑
        model.addAttribute("activeTab", "companies");
        return "weddingcompany";  // 对应 weddingcompany.html
    }
    
    @GetMapping("/{id}")
    public String showCompany(@PathVariable Long id, Model model) {
        // TODO: 添加查看具体婚庆公司的逻辑
        return "weddingcompany-detail";
    }
}