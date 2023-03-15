package com.yysw.contact;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;


@Controller
public class ContactController {
    @GetMapping("/contactUs")
    public String contact(Model model) {
        model.addAttribute("contactInformation", new ContactInformation());
        return "contact-us.html";
    }

    @PostMapping("/contactUs")
    public String submitContact(@Valid @ModelAttribute("contactInformation") ContactInformation contactInformation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "contact-us.html";
        } else {
            return "contact-success.html";
        }
    }
}
