package com.yysw.site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart.html";
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model) {
        List<AiModel> catalogue = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            AiModel tmp = new AiModel();
            tmp.setName("Egg " + i);
            catalogue.add(tmp);
        }
        model.addAttribute("catalogue", catalogue);
        return "catalogueMain.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        model.addAttribute("paymentInformation", new PaymentInformation());
        return "payment.html";
    }

    @PostMapping("/payment")
    public String submitPayment(@Valid @ModelAttribute("paymentInformation") PaymentInformation paymentInformation, BindingResult bindingResult) {
        System.out.println(paymentInformation.getExpiry());
        System.out.println(paymentInformation.getCvv());
        if (bindingResult.hasErrors()) {
            return "payment.html";
        } else {
            return "payment_success.html";
        }
    }
}