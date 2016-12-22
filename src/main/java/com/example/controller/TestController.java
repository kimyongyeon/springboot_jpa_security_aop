package com.example.controller;

import com.example.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String index() {
        return "index";
    }

    @RequestMapping("/main")
    public String main() {
        return "biz/main";
    }

}
