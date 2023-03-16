package com.yysw.user.owner;

import com.yysw.aimodels.AIModel;
import com.yysw.aimodels.AIModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class OwnerController {
    @Autowired
    AIModelRepository aiModelRepository;

    /**
     * TODO: add html page
     * prolly something like a form that has all the fields
     * redirects back to itself so you can add more
     * way to exit back to home page or marketplace
     */
    @GetMapping("/add-model")
    public String addModel(Model model) {
        model.addAttribute("newModel", new AIModel());
        return("add-model.html");
    }

    /**
     * TODO: Some checks are necessary for new model
     * Either through validation in this method or like PaymentInformation using javax
     */
    @PostMapping("/add-model")
    public String submitModel(
            @Valid @ModelAttribute("newModel") AIModel aiModel) {
        System.out.println(aiModel.toString());
        aiModelRepository.save(aiModel);
        return "index.html";
    }

}
