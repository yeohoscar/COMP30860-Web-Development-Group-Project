package com.yysw.cart;

import com.yysw.general.AIModel;
import com.yysw.general.AIModelRepository;
import com.yysw.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AIModelRepository aiModelRepository;

    public void updateCart() {
        System.out.println("rubingfffffff out1");
        List<AIModel> newCart = customerRepository.findCartById(1L);
        System.out.println("rubingfffffff out2w");
        newCart.add(aiModelRepository.findAiModelById(1L));
        System.out.println("rubingfffffff out3");
        customerRepository.updateCart(newCart, 1L);
        System.out.println("******* +" + newCart.size());
    }
    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        System.out.println("rubingggggg In");
        updateCart();
        System.out.println("rubingfffffff out");
        //TODO: just random value, no data storing, missing price etc.. need change
        List<AIModel> modelsInCart = new ArrayList<>();
//        modelsInCart = customerRepository.findCartById(1L);
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




}
