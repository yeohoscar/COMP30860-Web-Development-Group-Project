package com.yysw.site;

import com.yysw.user.customer.Customer;
import com.yysw.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String home() {
        customerRepository.save(customer("Egg", "Egg"));
        if (customerRepository.exists(Example.of(customer("Egg", "Egg")))) {
            return "payment.html";
        }
        return "index.html";
    }

    private Customer customer(String username, String passwd) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPasswd(passwd);
        return customer;
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

    @PostMapping("/login")
    public String loginAcc() {
        /* Im half asleep so idk what im doing but
           idea here is that you create an object for @RequestBody annotated method parameter that holds
           the username and password. Then you authenticate it against the database. Redirect back to login
           if wrong else, redirect to god knows where.
         */
        return "";
    }
}