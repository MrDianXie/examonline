package com.xjh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("test.do")
    public String test(Model model){
        model.addAttribute("name","谢金宏");
        model.addAttribute("age","18");
        return "main";
    }
}
