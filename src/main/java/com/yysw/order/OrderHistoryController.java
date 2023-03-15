package com.yysw.order;

import com.yysw.user.User;
import com.yysw.user.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderHistoryController{
    @Autowired
    private OrderRepository orderRepository;

    //DO NOT DELETE THIS METHOD!!!
    @GetMapping("/order-history")
    public String orderHistory(Customer customer, ModelMap modelMap, HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        // session user wont be null because order history can only access by user after login
        List<Order> orders;
        // TODO: CHECK IF USER IS OWNER OR CUSTOMER
        if (sessionUser instanceof Customer) {
            orders = orderRepository.findByCustomerOrderByOrderDateDesc(customer);
        } else {
            orders = orderRepository.findAllByOrderByOrderDateDescStateAsc();
        }

        modelMap.addAttribute("orders", orders);
        modelMap.addAttribute("user", sessionUser);

        return "order-history.html";
    }

    @GetMapping("/view-order/{id}")
    public String viewOrder(ModelMap modelMap, @PathVariable Long id, HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        // session user wont be null because order history can only access by user after login
        modelMap.addAttribute("order", orderRepository.findById(id));
        modelMap.addAttribute("user", sessionUser);

        return "view-order.html";
    }

    /*@GetMapping("/view-order-owner/{id}")
    public String viewOrderChangeState(Model model, @PathVariable Long id) {
        model.addAttribute("order", orderRepository.findById(id));
        return "view-all-orders";
    }*/

    @PostMapping("/view-order/{id}")
    public String orderChangeState(@ModelAttribute("order") Order order, @PathVariable Long id) {
        Order orderStateToBeUpdated = orderRepository.findOrderById(id);
        orderStateToBeUpdated.updateState(order);
        orderRepository.save(orderStateToBeUpdated);
        return "view-all-orders";
    }
}