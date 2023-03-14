package com.yysw.cart;

import com.yysw.aimodels.*;
import com.yysw.user.customer.Customer;
import com.yysw.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AIModelRepository aiModelRepository;

    private void updateCart() {
        Customer tmp = customerRepository.findCustomerByUser_id(1L);
        tmp.getCart().add(aiModelRepository.findAiModelById(1L));
        customerRepository.save(tmp);
    }
    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        updateCart();
        //TODO: just random value, no data storing, missing price etc.. need change
        List<AIModel> modelsInCart = customerRepository.findCustomerByUser_id(1L).getCart();
        model.addAttribute("size",modelsInCart.size());
        model.addAttribute("products",modelsInCart);
        double sub=0.0;
        double processfee=200;
        for (AIModel a:modelsInCart) {
//            sub += a.g;
        }
        model.addAttribute("subtotal", sub);
        model.addAttribute("fee", processfee);
        model.addAttribute("discount", sub*0.2);
        model.addAttribute("tot",sub+processfee-(sub*0.2));

        return "shoppingCart.html";
    }




}
