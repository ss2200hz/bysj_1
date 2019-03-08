package com.example.bysj_1;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;

@MapperScan("com.example.bysj_1.dao")
@SpringBootApplication
public class Bysj1Application {

	public static void main(String[] args) {
//		TemplateEngine templateEngine = new TemplateEngine();
//		templateEngine.addDialect(new LayoutDialect());
		SpringApplication.run(Bysj1Application.class, args);
	}

}

