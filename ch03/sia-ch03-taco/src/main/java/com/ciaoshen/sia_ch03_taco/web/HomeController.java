package com.ciaoshen.sia_ch03_taco.web;

// import javax.validation.Valid;
import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
// import com.ciaoshen.sia_ch03_taco.Order;
// import com.ciaoshen.sia_ch03_taco.Taco;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String processRequest() {
        return "home"; 
    }

}