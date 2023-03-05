package com.yysw.cart;

import com.yysw.site.AiModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Controller
public class ShoppingCartController {
    ArrayList<AiModel> modelsInCart = new ArrayList<>();

    //need to add the other shoppingcart's getMapping when merging branches
    @GetMapping("/ShoppingCart")
    public String shoppingCart(Model model) {
        model.addAttribute("size",modelsInCart.size());
        model.addAttribute("products",modelsInCart);
        double sub=0.0;
        double processfee=200;
        //TODO: add price together
//        for (AiModel a:modelsInCart) {
//            sub += a.price();
//        }
        model.addAttribute("subtotal", sub);
        //TODO: this is just a random value, change later
        model.addAttribute("fee", processfee);
        model.addAttribute("discount", sub*0.2);
        model.addAttribute("tot",sub+processfee-(sub*0.2));
        return "shoppingCart.html";
    }


}
