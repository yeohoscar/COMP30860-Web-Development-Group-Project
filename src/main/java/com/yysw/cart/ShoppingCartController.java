package com.yysw.cart;

import com.yysw.site.AiModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class ShoppingCartController {

    @PostMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        return "shoppingCart.html";
    }


}
