package com.qa.demo;

import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		
		Object byName = context.getBean("greeting");
		LocalTime byType = context.getBean(LocalTime.class);
		String byBoth = context.getBean("greeting", String.class);
		
		System.out.println(byName);
		System.out.println(byType);
		System.out.println(byBoth);
		
	}
	
	@Bean
	public LocalTime greeting() {
		return LocalTime.now();
//		return "Hello, World";
	}
		
		
//		Car car = context.getBean(Car.class);
//		
//		car.drive();
//	}

}
