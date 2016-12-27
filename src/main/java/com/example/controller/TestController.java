package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * Created by kimyongyeon on 2016-12-21.
 * controller - service - repository - model
 */
@Controller
public class TestController {
    @RequestMapping("/ctest")
    public String test() {
        return "controller test ";
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("message", "kimyongyeon");
        model.put("time", new Date());
        return "index";
    }

    @RequestMapping("/main")
    public String main() {
        return "biz/main";
    }

    @RequestMapping("/test2")
    public String test2(Model model) {
        model.addAttribute("message", "test");
        return "test/test";
    }

    @RequestMapping("/rest_test")
    public String rest_test(Model model) {
        model.addAttribute("message", "test");
        return "rest/rest";
    }


}
