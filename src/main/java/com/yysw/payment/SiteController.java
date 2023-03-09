package com.yysw.payment;

import com.yysw.general.AIModel;
import com.yysw.user.User;
import com.yysw.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class SiteController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        //TODO: just random value, no data storing, missing price etc.. need change
        List<AIModel> modelsInCart = new ArrayList<>();
        model.addAttribute("size",modelsInCart.size());
        model.addAttribute("products",modelsInCart);
        double sub=0.0;
        double processfee=200;
//        for (AiModel a:modelsInCart) {
//            sub += a.price();
//        }
        model.addAttribute("subtotal", sub);
        model.addAttribute("fee", processfee);
        model.addAttribute("discount", sub*0.2);
        model.addAttribute("tot",sub+processfee-(sub*0.2));

        return "shoppingCart.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
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
    public String submitPayment(
            @Valid @ModelAttribute("paymentInformation") PaymentInformation paymentInformation,
            BindingResult bindingResult
    ) {
        System.out.println(paymentInformation.getExpiry());
        System.out.println(paymentInformation.getCvv());
        if (bindingResult.hasErrors()) {
            return "payment.html";
        } else {
            return "payment_success.html";
        }
    }

    @PostMapping("/submit-login")
    public String loginAcc(@ModelAttribute("user") User user) {
        User repoUser = userRepository.findByUsernameAndPasswd(user.getUsername(), user.getPasswd());
        if (repoUser != null &&
                Objects.equals(repoUser.getUsername(), user.getUsername()) &&
                Objects.equals(repoUser.getPasswd(), user.getPasswd())
        ) {
                /*TODO: smth about differentiating owner and customer acc
                        and persisting login
                 */
            return "index.html";
        }
        return "login.html";
    }
}