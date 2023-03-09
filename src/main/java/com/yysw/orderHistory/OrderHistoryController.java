package com.yysw.orderHistory;

import com.yysw.order.Order;
import com.yysw.order.OrderRepository;
import com.yysw.user.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class OrderHistoryController{
    @Autowired
    private OrderRepository orderRepository;
    List<Order> orders = new ArrayList<>();

   //DO NOT DELETE THIS METHOD!!!
    @GetMapping("/orderHistory")
    public String orderHistory(Customer customer, Model model)
    {
        orders = orderRepository.findByCustomerOrderByOrderDateDesc(customer);
        model.addAttribute("orders", orders);
        return "orderHistory.html";
    }

    //this one is the one with specific order ID, DO NOT DELETE!!!
    @GetMapping("/viewOrder/{id}")
    public String viewOrder(Model model, @PathVariable int id)
    {
        model.addAttribute("view", orders.get(id));
        return "viewOrder.html";
    }

}
