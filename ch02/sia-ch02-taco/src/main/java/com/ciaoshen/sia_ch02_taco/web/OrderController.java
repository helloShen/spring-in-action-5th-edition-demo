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
@RequestMapping("/orders")
public class OrderController {

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
            /*
             * 会报错500：template can not extract "/design.html"
             * 因为使用design.html模板需要一个Taco对象。构建Taco对象比较复杂，
             * 在DesignTacoController已经实现了构建Taco对象。所以这里直接跳转到/design最简单。
             */
            // return "design"; 
            return "redirect:/design";
        }
        // Save the taco design...
        // We'll do this in chapter 3
        log.info("Processing design: " + design);
        return "redirect:/orders/new"; // 本章暂不涉及数据传递，所以只是简单的页面跳转。简单模拟点餐结束后完成整个点单和送货地址记录过程。
    }

    @GetMapping("/new")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

}