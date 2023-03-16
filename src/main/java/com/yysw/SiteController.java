package com.yysw;

import com.yysw.order.*;
import com.yysw.payment.PaymentInformation;
import com.yysw.login.RegisterInformation;
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

import javax.servlet.http.HttpSession;
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

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        if (sessionUserID == null) {
            return "index.html";
        } else {
            User repoUser = userRepository.findUserById(sessionUserID);
            model.addAttribute("user", repoUser);
        }
        return "index.html";
    }
}