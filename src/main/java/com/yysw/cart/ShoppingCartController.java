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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class ShoppingCartController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AIModelRepository aiModelRepository;

    @GetMapping("/catalogue")
    public String marketplace(Model model, HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");

        List<AIModel> modelsToDisplay;
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

    @PostMapping("/catalogue/{id}/{name}")
    public @ResponseBody void addCart(@ModelAttribute("model") AIModel aiModel, @PathVariable(value="id") Long id,
                                        @PathVariable(value="name") String name, Model model,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        User sessionUser = (User) request.getSession().getAttribute("user");
        AIModel ai = aiModelRepository.findAIModelById(id);

        if (sessionUser != null) {
            if (sessionUser instanceof Customer) {
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.setItem(ai);
                if (request.getParameter("trained") != null) {
                    shoppingCartItem.setPrice(ai.getTrainedPrice());
                    shoppingCartItem.setTrainedModelOrNot(true);
                } else {
                    shoppingCartItem.setPrice(ai.getUntrainedPrice());
                    shoppingCartItem.setTrainedModelOrNot(false);
                }
                shoppingCartItem.setCustomer((Customer) sessionUser);
                updateCustomerCart(sessionUser.getId(), shoppingCartItem);
            } else {
                ai.updateModel(aiModel);
                System.out.println(ai.isAvailable());
                aiModelRepository.save(ai);
            }
        }
        model.addAttribute("model", ai);

        assert ai != null;
        response.sendRedirect("/catalogue/" + id + "/" + ai.getModelName());
    }

    @PostMapping("/remove-cart-item/{id}")
    public @ResponseBody void removeCartItem(@PathVariable(value="id") Long id,
                                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer sessionUser = (Customer) request.getSession().getAttribute("user");
        List<ShoppingCartItem> cart = customerRepository.findCustomerById(sessionUser.getId()).getCart();
        System.out.println("hi");
        for (ShoppingCartItem s : cart) {
            System.out.println(s.getItem().toString());
        }
        cart.removeIf(item -> Objects.equals(item.getId(), id));
        System.out.println("bye");
        for (ShoppingCartItem s : cart) {
            System.out.println(s.getItem().toString());
        }

        response.sendRedirect("/shopping-cart");
    }

    public void updateCustomerCart(Long id, ShoppingCartItem item) {
        Customer customer = customerRepository.findCustomerById(id);
        customer.getCart().add(item);
        customerRepository.save(customer);
    }

    public void updateItemInCart(Customer customer, String option, Long itemId) {
        List<ShoppingCartItem> cart = customerRepository.findCustomerById(customer.getId()).getCart();

        System.out.println("OPTION passed in: "+ option);

        System.out.println("before");
        System.out.println(cart);

        for (ShoppingCartItem s : cart) {
            if (s.getItem().getId() == itemId) {
                if (option == "trained") {
                    s.setTrainedModelOrNot(true);
                } else {
                    s.setTrainedModelOrNot(false);
                }
            }
        }

        System.out.println("after");
        System.out.println(cart);


        customerRepository.save(customer);

        System.out.println("double check:");
        System.out.println(customer.getCart());

    }

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, HttpServletRequest request) {
        Customer customer = (Customer) request.getSession().getAttribute("user");
        List<ShoppingCartItem> userCart = customerRepository.findCustomerById(customer.getId()).getCart();
        model.addAttribute("size", userCart.size());
        model.addAttribute("products", userCart);
        double sub = 0.0;
        for (ShoppingCartItem item : userCart) {
            sub += item.getPrice();
        }
        model.addAttribute("subtotal", sub);

        return "shopping-cart.html";
    }

    @GetMapping("/shopping-cart/{id}/{option}")
    public void updateCartItem(@PathVariable(value="id") Long id, @PathVariable(value="option") String option, HttpServletRequest request, HttpServletResponse response) throws IOException {
        updateItemInCart((Customer) request.getSession().getAttribute("user"), option, id);
        System.out.println("METHOD update cart item called");
        response.sendRedirect("/shopping-cart");
    }
}
