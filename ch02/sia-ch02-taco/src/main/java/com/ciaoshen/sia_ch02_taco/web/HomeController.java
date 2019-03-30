package com.ciaoshen.sia_ch02_taco.web;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import com.ciaoshen.sia_ch02_taco.Order;
import com.ciaoshen.sia_ch02_taco.Taco;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    // 主页显示design.html
    @GetMapping
    public String designForm(Model model) {
        model.addAttribute("taco", new Taco());
        return "home";
    }

    // 从orderForm填完点餐单回到主页
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/orders/new";
            // return "home";  // 假装表单合格，为了方便测试，否则填正确表单太麻烦。
        }
        log.info("Processing order: " + order);
        return "home"; 
    }

}