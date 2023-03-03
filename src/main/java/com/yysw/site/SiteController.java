package com.yysw.site;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class SiteEGgController {
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
    public String register() {
        return "register.html";
    }

    @GetMapping
    public String payment() {
        return "payment.html";
    }

    @PostMapping("/payment")
    public String submitPayment(@Valid @ModelAttribute("paymentInformation") PaymentInformation paymentInformation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "payment.html";
        } else {
            return "payment_success.html";
        }
    }
}