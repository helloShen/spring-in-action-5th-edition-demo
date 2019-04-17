package com.ciaoshen.sia_ch03_taco.web;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import com.ciaoshen.sia_ch03_taco.Taco;
import com.ciaoshen.sia_ch03_taco.Order;
import com.ciaoshen.sia_ch03_taco.Ingredient;
import com.ciaoshen.sia_ch03_taco.Ingredient.Type;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
// import org.springframework.stereotype.Repository;
import com.ciaoshen.sia_ch03_taco.data.IngredientRepository;
import com.ciaoshen.sia_ch03_taco.data.JdbcIngredientRepository;
import com.ciaoshen.sia_ch03_taco.data.TacoRepository;
import com.ciaoshen.sia_ch03_taco.data.JdbcTacoRepository;
import com.ciaoshen.sia_ch03_taco.data.OrderRepository;
import com.ciaoshen.sia_ch03_taco.data.JdbcOrderRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            if (log.isInfoEnabled()) {
                log.info("errors = {}", errors.toString());
            }
            return "redirect:/design";
        }
        Taco saved = designRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/new";
    }

    /** 书上的错误： 书上没有列出这个函数。需要补上。 */
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    
    }

}