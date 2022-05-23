package com.qa.demo.domain;

import org.springframework.stereotype.Component;

//@Component
public class Driver {
	public Driver() {
		System.out.println("Driver created");
	}
	
	public void drive() {
		System.out.println("We love a good drive");
	}
	
}
