package com.SpringBoot.CoffeeBean_BackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class CoffeeBeanBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeBeanBackEndApplication.class, args);
	}

}

