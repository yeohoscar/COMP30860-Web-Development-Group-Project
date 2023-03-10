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
    HashMap<Long, Order> item = new HashMap<>();

    //DO NOT DELETE THIS METHOD!!!
    @GetMapping("/orderHistory")
    public String orderHistory(Customer customer, Model model)
    {
        orders = orderRepository.findByCustomer(customer);
        for(Order a: orders)
        {
            item.put(a.getOrder_id(), a);
        }
        model.addAttribute("customerOrders", orders);
        return "orderHistory.html";
    }


    @GetMapping("/viewOrder/{id}")
    public String viewOrder(Model model, @PathVariable Long id)
    {
        System.out.println("Successful"+id);
        model.addAttribute("view", item.get(id));
        return "viewOrder.html";
    }
}