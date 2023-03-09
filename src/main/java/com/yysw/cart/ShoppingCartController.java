package com.yysw.cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShoppingCartController {
    @PostMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        return "shoppingCart.html";
    }


}
