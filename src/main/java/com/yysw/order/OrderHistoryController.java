package com.yysw.order;

<<<<<<< HEAD
import com.yysw.aimodels.AIModel;
=======
>>>>>>> main
import com.yysw.order.Order;
import com.yysw.order.OrderRepository;
import com.yysw.user.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
=======
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> main

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class OrderHistoryController{
    @Autowired
    private OrderRepository orderRepository;
<<<<<<< HEAD
    HashMap<Long, Order> item = new HashMap<>();

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
=======

    //DO NOT DELETE THIS METHOD!!!
    @GetMapping("/orderHistory")
    public String orderHistory(Customer customer, Model model)
    {
        List<Order> orders = orderRepository.findByCustomerOrderByOrderDateDesc(customer);
        model.addAttribute("customerOrders", orders);
        return "orderHistory.html";
    }

    @GetMapping("/viewOrder/{id}")
    public String viewOrder(Model model, @PathVariable Long id)
    {
        System.out.println("Successful"+id);
        model.addAttribute("view", orderRepository.findById(id));
        return "viewOrder.html";
>>>>>>> main
    }
}