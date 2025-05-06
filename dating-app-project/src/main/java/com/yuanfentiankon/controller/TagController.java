package com.yuanfentiankon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")  // 修改为 /tag
public class TagController {
    
    @GetMapping("")
    public String tagPage() {
        return "tag";  // 将跳转到 tag.html
    }
}