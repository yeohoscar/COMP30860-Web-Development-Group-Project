package com.yysw.orderHistory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderHistoryController {
    @GetMapping("/orderHistory")
    public String orderHistory()
    {
        return "orderHistory.html";
    }
}
