package com.yysw.order;

import com.yysw.user.User;
import com.yysw.user.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        modelMap.addAttribute("order", orderRepository.findOrderById(id));
        modelMap.addAttribute("user", sessionUser);

        return "view-order.html";
    }

    @PostMapping("/view-order/{id}")
    public @ResponseBody void orderChangeState(@ModelAttribute("order") Order order, @PathVariable Long id, HttpServletResponse response) {
        Order orderStateToBeUpdated = orderRepository.findOrderById(id);
        orderStateToBeUpdated.updateState(order);
        orderRepository.save(orderStateToBeUpdated);
        try {
            response.sendRedirect("/order-history");
        } catch(IOException e) {e.printStackTrace();}
    }
}