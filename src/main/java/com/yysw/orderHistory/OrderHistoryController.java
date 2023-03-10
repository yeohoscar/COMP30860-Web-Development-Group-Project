package com.yysw.orderHistory;

import com.yysw.order.Order;
import com.yysw.order.OrderRepository;
import com.yysw.order.OrderedModel;
import com.yysw.user.customer.Customer;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderHistoryController{
    @Autowired
    private OrderRepository orderRepository;
    List<Order> orders = new ArrayList<>();
    HashMap<Order, Double> item = new HashMap<>();
    HashMap<Long, Order> ITEM = new HashMap<>();



//    @GetMapping("/orderHistory")
//    public String orderHistory(Customer customer, Model model)
//    {
//        double totalPrice= 0.0;
//        orders = orderRepository.findByCustomer(customer);
//        for(Order a: orders)
//        {
//            ITEM.put(a.getOrder_id(), a);
//
//        }
//
//        model.addAttribute("customerOrders", ITEM.entrySet());
//
//        return "orderHistory.html";
//    }


    //this one is the one with specific order ID, DO NOT DELETE!!!
//    @GetMapping("/viewOrder/{id}")
//    public String viewOrder(Model model, @PathVariable Long id)
//    {
//        System.out.println("Successful"+id);
////        HashMap<Order, Double> specificOrder = new HashMap<>();
////        specificOrder.put(order, item.get(order));
////        model.addAttribute("view", specificOrder.entrySet());
//        model.addAttribute("view", ITEM.get(id));
//
//        return "viewOrder.html";
//    }


    //DO NOT DELETE THIS METHOD!!!
    @GetMapping("/orderHistory")
    public String orderHistory(Customer customer, Model model)
    {
        double totalPrice= 0.0;
        orders = orderRepository.findByCustomer(customer);
        for(Order a: orders)
        {
            for(OrderedModel m: a.getOrderedModels())
            {
                totalPrice += m.getPrice();
            }
            item.put(a, totalPrice);
        }
        for(Map.Entry<Order, Double> a: item.entrySet())
        {
            System.out.println("key = "+a.getKey().getOrder_id());
        }
        model.addAttribute("customerOrders", item.entrySet());

        return "orderHistory.html";
    }


    @GetMapping("/viewOrder/{id}")
    public String viewOrder(Model model, @PathVariable Long id)
    {
        System.out.println("Successful"+id);
        HashMap<Order, Double> specificOrder = new HashMap<>();
        for(Map.Entry<Order, Double> a: item.entrySet())
        {
            if(a.getKey().getOrder_id()==id)
            {
                specificOrder.put(a.getKey(), a.getValue());
            }
            System.out.println("key = "+a.getKey().getOrder_id());
        }
        model.addAttribute("views", specificOrder.entrySet());

        return "viewOrder.html";
    }






}
