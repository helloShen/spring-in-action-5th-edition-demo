package com.ciaoshen.sia_ch01_taco;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiaCh01TacoHomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}