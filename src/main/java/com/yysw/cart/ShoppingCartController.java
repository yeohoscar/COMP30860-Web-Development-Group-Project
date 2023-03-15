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
import javax.servlet.http.HttpSession;
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
                if (request.getParameter("trained") != null) {
                    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                    shoppingCartItem.setItem(ai);
                    shoppingCartItem.setPrice(ai.getTrainedPrice());
                    shoppingCartItem.setTrainedModel(request.getParameter("trained") != null);
                    updateCustomerCart(sessionUser.getId(), shoppingCartItem);
                }

                /*if (request.getParameter("untrained") != null) {
                    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                    shoppingCartItem.setItem(ai);
                    shoppingCartItem.setPrice(ai.getUntrainedPrice());
                    shoppingCartItem.setTrainedModel(request.getParameter("untrained") != null);
                    updateCustomerCart(sessionUser.getId(), shoppingCartItem);
                }*/
            } else {
                //aiModel.setAvailable(request.getParameter("available") != null);
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
    public String removeCartItem(@PathVariable(value="id") Long id, HttpServletRequest request) {
        Customer sessionUser = (Customer) request.getSession().getAttribute("user");
        List<ShoppingCartItem> cart = customerRepository.findCustomerById(sessionUser.getId()).getCart();
        cart.removeIf(item -> Objects.equals(item.getId(), id));
        return "shopping-cart.html";
    }

    public void updateCustomerCart(Long id, ShoppingCartItem item) {
        Customer customer = customerRepository.findCustomerById(id);
        customer.getCart().add(item);
        customerRepository.save(customer);
    }

/*    @Autowired
    private ShoppintCartRespository shoppintCartRespository;
    List<ShoppingCartItem> modelsInCart = new ArrayList<>();

    @GetMapping("/marketplace")
    public String marketplace(HttpServletRequest request, Model model) {
        User sessionUser = (User) request.getSession().getAttribute("username");
        if(sessionUser == null)
        {
            model.addAttribute("catalogue", aiModelRepository.findAll());
            return "marketplace.html";

        }
        else {
            User repoUser = userRepository.findUserById(sessionUser.getId());
            model.addAttribute("catalogue", aiModelRepository.findAll());
            model.addAttribute("user", repoUser);
            return "marketplace.html";
        }

    }

    @GetMapping("/marketplace/{id}/{name}")
    public String viewModel(@PathVariable(value="id") Long id, @PathVariable(value="name") String name, Model model, HttpServletRequest request) {

        System.out.println("model namehbjh = "+name);
        System.out.println("model id = "+aiModelRepository.findAIModelById(id).getId());
        User sessionUser = (User) request.getSession().getAttribute("username");
        if(sessionUser == null)
        {
            model.addAttribute("model", aiModelRepository.findAIModelById(id));
            return "modelDetail.html";
        }
        else {
            User repoUser = userRepository.findUserById(sessionUser.getId());
            model.addAttribute("model", aiModelRepository.findAIModelById(id));
            model.addAttribute("user", repoUser);
            return "modelDetail.html";
>>>>>>> shopping-cart*/


    //we add the items that customer buy into database
   /* @PostMapping("/marketplace/{id}/{name}")
    public String addCart(ShoppingCartItem shoppingCartItem1, ShoppingCartItem shoppingCartItem2, @PathVariable(value="id") Long id, @PathVariable(value="name") String name, Model model, HttpServletRequest request, BindingResult bindingResult) {
//        System.out.println("model name = "+name);
//        System.out.println("model id = "+id);
        HttpSession session = request.getSession();
        if(session.getAttribute("username") != null) {
            System.out.println("User exists");
            Customer customer = (Customer) session.getAttribute("username");
            System.out.println("Obtain Customer name: "+customer.getUsername());
            AIModel ai = aiModelRepository.findAIModelById(id);
            //我们需要考虑当user什么都没选但是点击了 Add To Cart button的情况
            //        if(request.getParameter("trained") == null && request.getParameter("untrained") == null)
            //        {
            //
            //        }
            //如果是trained
            if (request.getParameter("trained") != null) {
                System.out.println("Trained is true\n");
                shoppingCartItem1.setItem(ai);
                shoppingCartItem1.setTrainedModel(true);
                shoppingCartItem1.setPrice(ai.getTrainedPrice());
                shoppingCartItem1.setCustomer(customer);
                modelsInCart.add(shoppingCartItem1);
                updateCustomerCart(customer);
                shoppintCartRespository.save(shoppingCartItem1);
                model.addAttribute("model", ai);
            }
            //如果是untrained
            else if (request.getParameter("untrained") != null) {
                System.out.println("Untrained is true\n");

                shoppingCartItem2.setItem(ai);
                shoppingCartItem2.setTrainedModel(false);
                shoppingCartItem2.setPrice(ai.getUntrainedPrice());
                shoppingCartItem2.setCustomer(customer);
                modelsInCart.add(shoppingCartItem2);
                //把当前用户选的model加入到customer的购物车
                updateCustomerCart(customer);
                shoppintCartRespository.save(shoppingCartItem2);

                model.addAttribute("model", ai);
            }
            //如果既是trained, 又是untrained
            else if (request.getParameter("trained") != null && request.getParameter("untrained") != null) {
                //set values for two objects
                shoppingCartItem1.setItem(ai);
                shoppingCartItem1.setTrainedModel(true);
                shoppingCartItem1.setPrice(ai.getTrainedPrice());
                shoppingCartItem1.setCustomer(customer);
                shoppintCartRespository.save(shoppingCartItem1);

                shoppingCartItem2.setItem(ai);
                shoppingCartItem2.setTrainedModel(false);
                shoppingCartItem2.setPrice(ai.getUntrainedPrice());
                shoppingCartItem2.setCustomer(customer);
                shoppintCartRespository.save(shoppingCartItem2);

                modelsInCart.add(shoppingCartItem1);
                updateCustomerCart(customer);
                modelsInCart.add(shoppingCartItem2);
                updateCustomerCart(customer);
                model.addAttribute("model", ai);
            }
            //        shoppingCartItem.setTrainedModel(request.getParameter("trained") != null);
            //        shoppingCartItem.setUntrainedModel(request.getParameter("untrained") != null);
            //        if (shoppingCartItem.isTrainedModel()) {
            //            if (shoppingCartItem.isUntrainedModel()) {
            //                shoppingCartItem.setPrice((ai.getTrainedPrice() + ai.getUntrainedPrice()));
            //            } else {
            //                shoppingCartItem.setPrice(ai.getTrainedPrice());
            //            }
            //        } else if(shoppingCartItem.isUntrainedModel()){
            //            shoppingCartItem.setPrice(ai.getUntrainedPrice());
            //        }

            if (bindingResult.hasErrors()) {
                return "index.html";
            } else {
                return "modelDetail.html";
            }
        }else{
//            response.sendRedirect(request.getContextPath() + "/logInAgain");
            return "logInAgain.html";
        }
    }*/


    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
//        addToCart(customer);
        List<ShoppingCartItem> userCart = customerRepository.findCustomerById(customer.getId()).getCart();
        //TODO: just random value, no data storing, missing price etc.. need change
//        modelsInCart = customerRepository.findCartById(1L);
        System.out.println("print items in customer cart");
        for (ShoppingCartItem s : userCart) {
            System.out.println(s.getItem());
        }
        model.addAttribute("size", userCart.size());
        model.addAttribute("products", userCart);
        double sub = 0.0;
//      double processfee=200;
        for (ShoppingCartItem item : userCart) {
            sub += item.getPrice();
        }
        model.addAttribute("subtotal", sub);
        /*model.addAttribute("fee", processfee);
        model.addAttribute("discount", sub*0.2);
        model.addAttribute("tot",sub+processfee-(sub*0.2));*/

        return "shopping-cart.html";
    }
}
