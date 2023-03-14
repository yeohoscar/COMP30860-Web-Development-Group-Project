package com.yysw.payment;

import com.yysw.user.User;
import com.yysw.user.UserRepository;
import com.yysw.user.customer.Customer;
import com.yysw.user.owner.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SiteController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "index.html";
    }
    @GetMapping("/successLogIn")
    public String successLogIn() {
        return "login-success.html";
    }
    @GetMapping("/logInAgain")
    public String logInAgain() {
        return "logInAgain.html";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login.html";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute(new RegisterInformation());
        return "register.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("RegisterInformation") RegisterInformation registerInformation) {
        if (registerInformation.getAdminKey() != null &&
                Objects.equals(registerInformation.getAdminKey(), "verycooladminkey")) {
            userRepository.save(new Owner(registerInformation.getUsername(), registerInformation.getPasswd()));
        } else {
            userRepository.save(new Customer(registerInformation.getUsername(), registerInformation.getPasswd()));
        }
        return "login.html";
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        model.addAttribute("paymentInformation", new PaymentInformation());
        return "payment.html";
    }

    @PostMapping("/payment")
    public String submitPayment(
            @Valid @ModelAttribute("paymentInformation") PaymentInformation paymentInformation,
            BindingResult bindingResult
    ) {
        System.out.println(paymentInformation.getExpiry());
        System.out.println(paymentInformation.getCvv());
        if (bindingResult.hasErrors()) {
            return "payment.html";
        } else {
            return "catalogueMain.html";
        }
    }

//    @PostMapping("/submit-login")
//    public String loginAcc(@ModelAttribute("user") User user) {
//        User repoUser = userRepository.findByUsernameAndPasswd(user.getUsername(), user.getPasswd());
//        if (repoUser != null &&
//                Objects.equals(repoUser.getUsername(), user.getUsername()) &&
//                Objects.equals(repoUser.getPasswd(), user.getPasswd())
//        ) {
//                /*TODO: smth about differentiating owner and customer acc
//                        and persisting login
//                 */
//            return "index.html";
//        }
//        return "login.html";
//    }
}