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
        ContactInformation c = new ContactInformation();
        model.addAttribute("contactInformation", c);
        return "contactUs.html";
    }

    @PostMapping("/contactUs")
    public String submitContact(@Valid @ModelAttribute("contactInformation") ContactInformation contactInformation, BindingResult bindingResult) {
        System.out.println(contactInformation.getContactName());
        System.out.println(contactInformation.getContactDate());
        System.out.println(bindingResult.toString());
        if (bindingResult.hasErrors()) {
            return "contactUs.html";
        } else {
            return "contactSuccess.html";
        }
    }
}
