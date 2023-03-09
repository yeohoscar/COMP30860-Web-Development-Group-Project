package com.yysw.marketplace;

import com.yysw.general.AIModel;
import com.yysw.general.AIModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
public class MarketplaceController {
    @Autowired
    private AIModelRepository aiModelRepository;

    @GetMapping("/marketplace")
    public String marketplace(Model model) {
        model.addAttribute("catalogue", aiModelRepository.findAll());

        return "marketplace.html";
    }

    @GetMapping("/marketplace/{id}/{name}")
    public String viewModel(@PathVariable(value="id") Long id, @PathVariable(value="name") String name, Model model) {
        model.addAttribute("model", aiModelRepository.findAIModelById(id));

        return "modelDetail.html";
    }

    // have to figure out how to link add to cart so its added when you press the button
    @PostMapping("/marketplace/{id}/{name}")
    public String addCart(@Valid @ModelAttribute("marketplaceInformation") MarketplaceInformation marketplaceInformation, @PathVariable(value="id") Long id, @PathVariable(value="name") String name, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index.html";
        } else {
            return "modelDetail.html";
        }
    }

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
