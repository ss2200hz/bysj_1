package com.example.bysj_1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.bysj_1.dao")
@SpringBootApplication
public class Bysj1Application {

	public static void main(String[] args) {
		SpringApplication.run(Bysj1Application.class, args);
	}

}

