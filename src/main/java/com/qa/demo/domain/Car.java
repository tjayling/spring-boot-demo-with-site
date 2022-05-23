package com.qa.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope("prototype")
public class Car {

	private Driver d;

	public Car(Driver d) {
		this.d = d;
		System.out.println("Car created");
//		this.drive();
	}

	public void drive() {
		System.out.println("Vroom");
		d.drive();
	}
}
