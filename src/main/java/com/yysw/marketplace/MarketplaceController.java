package com.yysw.marketplace;

import com.yysw.aimodels.AIModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MarketplaceController {
    @Autowired
    private AIModelRepository aiModelRepository;




    // have to figure out how to link add to cart so its added when you press the button


    /*@PostMapping("/marketplace")
    public String add(@Valid @ModelAttribute("marketplaceInformation") MarketplaceInformation marketplaceInformation, BindingResult bindingResult) {

        System.out.println(marketplaceInformation.getItem());
        System.out.println(marketplaceInformation.getQuantity());
        if (bindingResult.hasErrors()) {
            return "index.html";
        } else {
            return "modelDetail.html";
        }
    }*/
}
