package com.yysw.order;

import com.yysw.user.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderHistoryController{
    @Autowired
    private OrderRepository orderRepository;

    //DO NOT DELETE THIS METHOD!!!
    @GetMapping("/order-history")
    public String orderHistory(Customer customer, Model model) {
        List<Order> orders;
        // TODO: CHECK IF USER IS OWNER OR CUSTOMER
        if (false) {
            orders = orderRepository.findByCustomerOrderByOrderDateDesc(customer);
        } else {
            orders = orderRepository.findAllByOrderByOrderDateDescStateAsc();
        }

        model.addAttribute("orders", orders);
        return "order-history.html";
    }

    @GetMapping("/view-order/{id}")
    public String viewOrder(Model model, @PathVariable Long id) {
        model.addAttribute("order", orderRepository.findById(id));
        return "view-order.html";
    }

    @GetMapping("/view-order-change-state/{id}")
    public String viewOrderChangeState(Model model, @PathVariable Long id) {
        model.addAttribute("order", orderRepository.findById(id));
        return "view-all-orders";
    }

    @PostMapping("/view-order-change-state/{id}")
    public String orderChangeState(@ModelAttribute("order") Order order, @PathVariable Long id) {
        Order orderStateToBeUpdated = orderRepository.findOrderById(id);
        orderStateToBeUpdated.updateState(order);
        return "view-all-orders";
    }
}