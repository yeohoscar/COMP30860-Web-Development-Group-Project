package com.yysw.cart;

import com.yysw.aimodels.AIModel;
import com.yysw.aimodels.AIModelRepository;
import com.yysw.user.customer.Customer;
import com.yysw.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AIModelRepository aiModelRepository;
    List<ShoppingCartItem> modelsInCart = new ArrayList<>();

    @GetMapping("/marketplace")
    public String marketplace(Model model) {
        model.addAttribute("catalogue", aiModelRepository.findAll());

        return "marketplace.html";
    }

    @GetMapping("/marketplace/{id}/{name}")
    public String viewModel(@PathVariable(value="id") Long id, @PathVariable(value="name") String name, Model model) {

        System.out.println("model name = "+name);
        System.out.println("model id = "+aiModelRepository.findAIModelById(id).getId());
        model.addAttribute("model", aiModelRepository.findAIModelById(id));
        System.out.println("get here");
        return "modelDetail.html";
    }

//    @PostMapping("/marketplace/{id}/{name}")
//    public String addCart(ShoppingCartItem shoppingCartItem, @PathVariable(value="id") Long id, @PathVariable(value="name") String name, Model model, HttpServletRequest request, BindingResult bindingResult) {
//        AIModel ai = aiModelRepository.findAIModelById(id);
//        shoppingCartItem.setItem(ai);
//        shoppingCartItem.setTrainedModel(request.getParameter("trained") != null);
//        shoppingCartItem.setUntrainedModel(request.getParameter("untrained") != null);
//        if (shoppingCartItem.isTrainedModel()) {
//            if (shoppingCartItem.isUntrainedModel()) {
//                shoppingCartItem.setPrice((ai.getTrainedPrice() + ai.getUntrainedPrice()));
//            } else {
//                shoppingCartItem.setPrice(ai.getTrainedPrice());
//            }
//        } else {
//            shoppingCartItem.setPrice(ai.getUntrainedPrice());
//        }
//        model.addAttribute("model", shoppingCartItem.getItem());
//        modelsInCart.add(shoppingCartItem);
//
//        if (bindingResult.hasErrors()) {
//            return "index.html";
//        } else {
//            return "modelDetail.html";
//        }
//    }
    @PostMapping("/marketplace/{id}/{name}")
    public String addCart(ShoppingCartItem shoppingCartItem, @PathVariable(value="id") Long id, @PathVariable(value="name") String name, Model model, HttpServletRequest request, BindingResult bindingResult) {
//        System.out.println("model name = "+name);
//        System.out.println("model id = "+id);
        AIModel ai = aiModelRepository.findAIModelById(id);
        shoppingCartItem.setItem(ai);
        shoppingCartItem.setTrainedModel(request.getParameter("trained") != null);
        shoppingCartItem.setUntrainedModel(request.getParameter("untrained") != null);
        if (shoppingCartItem.isTrainedModel()) {
            if (shoppingCartItem.isUntrainedModel()) {
                shoppingCartItem.setPrice((ai.getTrainedPrice() + ai.getUntrainedPrice()));
            } else {
                shoppingCartItem.setPrice(ai.getTrainedPrice());
            }
        } else {
            shoppingCartItem.setPrice(ai.getUntrainedPrice());
        }
        model.addAttribute("model", shoppingCartItem.getItem());
        modelsInCart.add(shoppingCartItem);

        if (bindingResult.hasErrors()) {
            return "index.html";
        } else {
            return "modelDetail.html";
        }
    }
    public void addToCart() {
        Customer tmp = customerRepository.findCustomerByUser_id(1L);
        tmp.getCart().add(modelsInCart.get(modelsInCart.size()-1));
        //customerRepository.save(tmp);
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        addToCart();
        List<ShoppingCartItem> userCart = customerRepository.findCustomerByUser_id(1L).getCart();
        //TODO: just random value, no data storing, missing price etc.. need change
//        modelsInCart = customerRepository.findCartById(1L);
        for (ShoppingCartItem s : userCart) {
            System.out.println(s.getItem());
        }
        model.addAttribute("size", userCart.size());
        model.addAttribute("products", userCart);
        double sub = 0.0;
//      double processfee=200;
        for (ShoppingCartItem item : modelsInCart) {
            sub += item.getPrice();
        }
        model.addAttribute("subtotal", sub);
        /*model.addAttribute("fee", processfee);
        model.addAttribute("discount", sub*0.2);
        model.addAttribute("tot",sub+processfee-(sub*0.2));*/

        return "shoppingCart.html";
    }
}
