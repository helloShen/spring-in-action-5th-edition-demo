package com.ciaoshen.sia_ch03_taco.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;

import com.ciaoshen.sia_ch03_taco.Taco;
import com.ciaoshen.sia_ch03_taco.Order;
import com.ciaoshen.sia_ch03_taco.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
        SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepo.save(order);
        return "redirect:/orders/finish";
    }

    /** 这里书上是错的： 不能创建新new Order()，
     *  必须从session里调取DesignTacoController里创建的order对象 */
    @GetMapping("/new")
    public String orderForm(Model model, Order order) {
        model.addAttribute("order", order);
        return "orderForm";
    }

}