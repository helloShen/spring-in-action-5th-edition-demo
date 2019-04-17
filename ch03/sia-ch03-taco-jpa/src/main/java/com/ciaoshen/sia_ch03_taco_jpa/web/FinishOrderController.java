package com.ciaoshen.sia_ch03_taco_jpa.web;

import java.util.List;
import java.util.ArrayList;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;

import com.ciaoshen.sia_ch03_taco_jpa.Ingredient;
import com.ciaoshen.sia_ch03_taco_jpa.Order;
import com.ciaoshen.sia_ch03_taco_jpa.Taco;
import com.ciaoshen.sia_ch03_taco_jpa.data.TacoRepository;
import com.ciaoshen.sia_ch03_taco_jpa.data.OrderRepository;
import com.ciaoshen.sia_ch03_taco_jpa.data.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/orders/finish")
@SessionAttributes("order")
public class FinishOrderController {

    @GetMapping
    public String showOrder(Model model, Order order, SessionStatus sessionStatus) {
        model.addAttribute("myTacos", order.getTacos());

        model.addAttribute("name", order.getDName());
        model.addAttribute("street", order.getDStreet());
        model.addAttribute("city", order.getDCity());
        model.addAttribute("state", order.getDState());
        model.addAttribute("zip", order.getDZip());
        sessionStatus.setComplete();
        return "finish";
    }

}