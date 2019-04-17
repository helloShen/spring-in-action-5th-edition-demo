package com.ciaoshen.sia_ch03_taco_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SiaCh03TacoJpaApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(SiaCh03TacoJpaApplication.class, args);
	}

	/** 替代在controller类前写@RequestMapping("/design")的做法
	 *  本项目还是采用之前的做法 */
	// @Override
	// public void addViewControllers(ViewControllerRegistry registry) {
	// 	registry.addViewController("redirect:/design").setViewName("design");
	// }

}
