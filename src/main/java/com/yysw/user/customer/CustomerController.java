package com.yysw.user.customer;

import com.yysw.general.AIModel;
import com.yysw.general.AIModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    private AIModelRepository aiModelRepository;

    private void updateCart() {
        List<AIModel> newCart = customerRepository.findCartById(1);
        newCart.add(aiModelRepository.findAiModelById(1L));
        customerRepository.updateCart(newCart, 1);
    }

}
