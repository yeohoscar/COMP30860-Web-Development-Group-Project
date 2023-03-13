package com.yysw.user.owner;

import com.yysw.aimodels.AIModel;
import com.yysw.aimodels.AIModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OwnerController {
    @Autowired
    AIModelRepository aiModelRepository;

    /**
     * TODO: add html page
     * prolly something like a form that has all the fields
     * redirects back to itself so you can add more
     * way to exit back to home page or marketplce
     */
    @GetMapping("add-model")
    public String addModel(Model model) {
        model.addAttribute("newModel", new AIModel());
        return("add-model.html");
    }

    /**
     * TODO: Some checks are necessary for new model
     * Either through validation in this method or like PaymentInformation using javax
     */
    @PostMapping("add-model")
    public String addModel(@ModelAttribute("newModel") AIModel aiModel) {
        aiModelRepository.save(aiModel);
        return "add-model";
    }

    /**
     * TODO: not too sure about page
     * Most simple is to display all models, and have a checkbox that toggles
     * AIModel available field.
     */
    @GetMapping("toggle-models")
    public String toggleModels(Model model) {
        model.addAttribute("models", aiModelRepository.findAll());
        return "toggle-models";
    }

    @PostMapping("toggle-models")
    public String toggleModels(@ModelAttribute("models") List<AIModel> models) {
        aiModelRepository.saveAll(models);
        return "toggle-models.html";
    }
}
