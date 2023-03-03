package com.yysw.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SiteController {
    @GetMapping
    public String home() {
        return "index.html";
    }

    @GetMapping
    public String cart() {
        return "cart.html";
    }

    @GetMapping
    public String login() {
        return "login.html";
    }

    @GetMapping
    public String payment() {
        return "payment.html";
    }
}