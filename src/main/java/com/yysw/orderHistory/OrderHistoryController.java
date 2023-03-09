package com.yysw.orderHistory;

import com.yysw.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@Controller
public class OrderHistoryController {
    @Autowired
    private OrderRepository orderRepository;
    List<Order> orders = orderRepository.findByCustomerOrderByOrderDateDesc(Customer customer);
    //assume customers' orders are stored in the HashMap below
    HashMap<Integer, Order> myOrders = new HashMap<>();

   //DO NOT DELETE THIS METHOD!!!
    @GetMapping("/orderHistory")
    public String orderHistory(Model model)
    {
        model.addAttribute("orders", myOrders.values());
        return "orderHistory.html";
    }

    //this one is the one with specific order ID, DO NOT DELETE!!!
    @GetMapping("/viewOrder/{id}")
    public String viewOrder(Model model, @PathVariable int id)
    {
        model.addAttribute("view", myOrders.get(id));
        return "viewOrder.html";
    }



    //just for testing viewOrder, deletable
//    @GetMapping("/orderHistory")
//    public String orderHistory()
//    {
//        return "orderHistory.html";
//    }
//
//    @GetMapping("/viewOrder")
//    public String viewOrder()
//    {
//        return "viewOrder.html";
//    }


}
