package com.ciaoshen.sia_ch03_taco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SiaCh03TacoApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(SiaCh03TacoApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("redirect:/design").setViewName("design");
	}

}
