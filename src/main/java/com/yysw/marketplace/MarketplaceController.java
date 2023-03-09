package com.yysw.marketplace;

import com.yysw.general.AIModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class MarketplaceController {
    private HashMap<Integer, AIModel> catalogue = new HashMap<>();

    @GetMapping("/marketplace")
    public String marketplace(Model model) {
        for (int i = 0; i < 7; i++) {
            AIModel tmp = new AIModel();
            tmp.setId(i);
            tmp.setName("Egg" + i);
            tmp.setPhotoURL("/image/RemoveBG.jpg");
            tmp.setDescription("twinkle twinkle little star, how i wonder what you are, up above the world so high, like a diamond in the sky. Twinkle twinkle little star, how i wonder what you are.");
            catalogue.put(i, tmp);
        }

        model.addAttribute("catalogue", catalogue);

        return "marketplace.html";
    }

    @GetMapping("/marketplace/{id}/{name}")
    public String viewModel(@PathVariable(value="id") int id, @PathVariable(value="name") String name, Model model) {
        MarketplaceInformation marketplaceInformation = new MarketplaceInformation();
        marketplaceInformation.setItem(catalogue.get(id));
        marketplaceInformation.setQuantity(0);
        marketplaceInformation.setPrice(id);
        model.addAttribute("marketplaceInformation", marketplaceInformation);

        return "modelDetail.html";
    }

    @PostMapping("/marketplace/{id}/{name}")
    public String addCart(@Valid @ModelAttribute("marketplaceInformation") MarketplaceInformation marketplaceInformation, @PathVariable(value="id") int id, @PathVariable(value="name") String name, BindingResult bindingResult) {
        marketplaceInformation.setItem(catalogue.get(id));
        System.out.println(marketplaceInformation.getItem().getName());
        System.out.println(marketplaceInformation.getQuantity());
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
