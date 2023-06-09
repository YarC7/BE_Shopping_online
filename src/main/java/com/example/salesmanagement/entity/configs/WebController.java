package com.example.salesmanagement.entity.configs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = {"/", "/home"})
    public String homepage() {
        return "home.html"; // Trả về home.html
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello.html"; // Trả về hello.html
    }

}
