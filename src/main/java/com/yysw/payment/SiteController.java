package com.yysw.payment;

import com.yysw.order.*;
import com.yysw.user.*;
import com.yysw.user.customer.*;
import com.yysw.user.owner.*;
import com.yysw.cart.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Date;

@Controller
public class SiteController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private  OrderRepository orderRepository;

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
        return "login-again.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login.html";
    }

    @GetMapping("/logInOccupied")
    public String logInOccupied(Model model) {
        model.addAttribute("user", new User());
        return "login-occupied.html";
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
            HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "payment.html";
        } else {
            List<OrderedModel> orderedModels = new ArrayList<>();
            Customer customer = (Customer) request.getSession().getAttribute("user");
            for (ShoppingCartItem item : customer.getCart()) {
                OrderedModel orderedModel = new OrderedModel(item.getItem().getId(), item.getPrice());
                orderedModels.add(orderedModel);
            }
            Date d = Date.valueOf(LocalDate.now());

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

            String id = customer.getId() + "#" +  dtf.format(now);

            Order order = new Order(customer, orderedModels, State.NEW, d, id);
            orderRepository.save(order);

            return "catalogueMain.html";
        }
    }
}