package com.yysw.cart;

import com.yysw.aimodels.AIModel;
import com.yysw.aimodels.AIModelRepository;
import com.yysw.user.User;
import com.yysw.user.customer.Customer;
import com.yysw.user.customer.CustomerRepository;
import com.yysw.user.owner.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AIModelRepository aiModelRepository;
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

    @GetMapping("/catalogue")
    public String marketplace(Model model, HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");

        List<AIModel> modelsToDisplay;
        //TODO: CHECK IF USER IS OWNER OR CUSTOMER OR RANDOM SCRUB
        if (sessionUser == null) {
            modelsToDisplay = aiModelRepository.findAIModelByAvailable(true);
        } else {
            if (sessionUser instanceof Owner) {
                modelsToDisplay = aiModelRepository.findAll();
            } else {
                modelsToDisplay = aiModelRepository.findAIModelByAvailable(true);
            }
        }

        model.addAttribute("catalogue", modelsToDisplay);

        return "catalogue.html";
    }

    @GetMapping("/catalogue/{id}/{name}")
    public String modelDetails(@PathVariable(value="id") Long id, @PathVariable(value="name") String name,
                               Model model, HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");

        if (sessionUser != null) {
            model.addAttribute("user", sessionUser);
        }
        model.addAttribute("model", aiModelRepository.findAIModelById(id));

        return "model-detail.html";
    }

    /*@GetMapping("/catalogue/add-to-cart/{id}/{name}")
    public String viewModelDetails(@PathVariable(value="id") Long id,
                                   @PathVariable(value="name") String name, Model model) {
        model.addAttribute("model", aiModelRepository.findAIModelById(id));
        return "model-detail.html";
    }

    @GetMapping("/catalogue/edit/{id}/{name}")
    public String viewModel(@PathVariable(value="id") Long id,
                            @PathVariable(value="name") String name, Model model) {
        model.addAttribute("model", aiModelRepository.findAIModelById(id));
        return "model-detail.html";
    }*/

    @PostMapping("/catalogue/{id}/{name}")
    public @ResponseBody void addCart(@ModelAttribute("model") AIModel aiModel, @PathVariable(value="id") Long id,
                                        @PathVariable(value="name") String name, Model model,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        User sessionUser = (User) request.getSession().getAttribute("user");
        AIModel ai = aiModelRepository.findAIModelById(id);

        if (sessionUser != null) {
            if (sessionUser instanceof Customer) {
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
            } else {
                ai.updateModel(aiModel);
                aiModelRepository.save(ai);
            }
        }
        model.addAttribute("model", ai);

        response.sendRedirect("/catalogue/" + id + "/" + ai.getModelName());
    }

    /*@PostMapping("/catalogue/edit/{id}/{name}")
    public String editModel(@ModelAttribute("model") AIModel aiModel,
                            @PathVariable(value="id") Long id, @PathVariable(value="name") String name) {
        AIModel aiModelToBeUpdated = aiModelRepository.findAIModelById(id);
        aiModelToBeUpdated.updateModel(aiModel);
        return "model-detail.html"; // TODO: TEST AND REDIRECT TO A BETTER PLACE
    }*/

    public void addToCart() {
        Customer tmp = customerRepository.findCustomerByUser_id(1L);
        tmp.getCart().add(shoppingCartItem);
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

        model.addAttribute("subtotal", sub);
        /*model.addAttribute("fee", processfee);
        model.addAttribute("discount", sub*0.2);
        model.addAttribute("tot",sub+processfee-(sub*0.2));*/

        return "shopping-cart.html";
    }
}
