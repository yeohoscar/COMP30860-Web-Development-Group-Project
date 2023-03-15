package com.yysw.payment;

import com.yysw.user.User;
import com.yysw.user.UserRepository;
import com.yysw.user.customer.Customer;
import com.yysw.user.customer.CustomerRepository;
import com.yysw.user.owner.Owner;
import com.yysw.user.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SiteController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (sessionUser == null) {
            return "index.html";
        } else {
            User repoUser = userRepository.findUserById(sessionUser.getId());
            model.addAttribute("user", repoUser);
        }
        return "index.html";
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

    @GetMapping("/logInOccupied")
    public String logInOccupied(Model model) {
        model.addAttribute("user", new User());
        return "logInOccupied.html";
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
            ownerRepository.save((Owner) userRepository.findByUsernameAndPasswd(registerInformation.getUsername(), registerInformation.getPasswd()));
        } else {
            userRepository.save(new Customer(registerInformation.getUsername(), registerInformation.getPasswd()));
            customerRepository.save((Customer) userRepository.findByUsernameAndPasswd(registerInformation.getUsername(), registerInformation.getPasswd()));

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
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request
    ) {
        Customer sessionUser = (Customer) request.getSession().getAttribute("user");
        System.out.println(paymentInformation.getExpiry());
        System.out.println(paymentInformation.getCvv());
        if (bindingResult.hasErrors()) {
            return "payment.html";
        } else {
            model.addAttribute("paidCustomer", sessionUser);
//            return "successPayment.html";
            return "catalogue.html";
        }
    }
}