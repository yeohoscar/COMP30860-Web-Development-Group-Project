package com.yysw.cart;

import com.yysw.aimodels.AIModel;
import com.yysw.aimodels.AIModelRepository;
import com.yysw.user.User;
import com.yysw.user.UserRepository;
import com.yysw.user.customer.Customer;
import com.yysw.user.customer.CustomerRepository;
import com.yysw.user.owner.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AIModelRepository aiModelRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/catalogue")
    public String marketplace(Model model, HttpSession session) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        User sessionUser = userRepository.findUserById(sessionUserID);
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
                               Model model, HttpSession session) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        User sessionUser = userRepository.findUserById(sessionUserID);
        AIModel aiModel = aiModelRepository.findAIModelById(id);
        boolean hasItem = false;

        if (sessionUser != null) {
            model.addAttribute("user", sessionUser);
            if (sessionUser instanceof Customer) {
                List<ShoppingCartItem> cartItems = customerRepository.findCustomerById(sessionUser.getId()).getCart();
                for (ShoppingCartItem item : cartItems) {
                    if (item.getItem() == aiModel) {
                        hasItem = true;
                        break;
                    }
                }
            }
        }
        model.addAttribute("hasItem", hasItem);
        model.addAttribute("model", aiModel);

        return "model-detail.html";
    }

    @PostMapping("/catalogue/{id}/{name}")
    public @ResponseBody void addCart(@ModelAttribute("model") AIModel aiModel, @PathVariable(value="id") Long id,
                                        @PathVariable(value="name") String name, Model model,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long sessionUserID = (Long) request.getSession().getAttribute("user_id");
        User sessionUser = userRepository.findUserById(sessionUserID);
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

    @Transactional
    @PostMapping("/remove-cart-item/{id}")
    public String removeCartItem(@PathVariable(value="id") Long id,
                                 HttpSession session, HttpServletResponse response) throws IOException {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        Customer customer = customerRepository.findCustomerById(sessionUserID);
        shoppingCartRepository.deleteByIdAndCustomer(id, customer);

        return "redirect:/shopping-cart";
    }

    public void updateCustomerCart(Long id, ShoppingCartItem item) {
        Customer customer = customerRepository.findCustomerById(id);
        customer.getCart().add(item);
        customerRepository.save(customer);
    }

    public void updateItemInCart(String option, Long itemId, HttpServletRequest request) {
        Long sessionUserID = (Long) request.getSession().getAttribute("user_id");
        Customer customer = (Customer) userRepository.findUserById(sessionUserID);
        List<ShoppingCartItem> cart = customer.getCart();
        for (ShoppingCartItem s : cart) {
            if (s.getId() == itemId) {
                if (option.equals("trained")) {
                    s.setTrainedModelOrNot(true);
                    s.setPrice(s.getItem().getTrainedPrice());
                } else {
                    s.setTrainedModelOrNot(false);
                    s.setPrice(s.getItem().getUntrainedPrice());
                }
            }
        }
        customerRepository.save(customer);
        System.out.println(customer.getCart());
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, HttpSession session) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        List<ShoppingCartItem> userCart = customerRepository.findCustomerById(sessionUserID).getCart();
        System.out.println("okay");
        for (ShoppingCartItem s : userCart) {
            System.out.println(s.getItem().toString());
        }

        model.addAttribute("size", userCart.size());
        model.addAttribute("products", userCart);
        double sub = 0.0;
        for (ShoppingCartItem item : userCart) {
            sub += item.getPrice();
        }
        model.addAttribute("subtotal", sub);

        return "shopping-cart.html";
    }

    @GetMapping("/shopping-cart/{id}")
    public void updateCartItem(@PathVariable(value="id") Long id, @RequestParam("selectOption") String option, HttpServletRequest request, HttpServletResponse response) throws IOException {
        updateItemInCart(option, id, request);
        System.out.println("METHOD update cart item called");
        response.sendRedirect("/shopping-cart");
        System.out.println("redirect happened");
    }

    @PostMapping("/checkoutpayment")
    public String checkOut() {
        return "payment.html";
    }
}
