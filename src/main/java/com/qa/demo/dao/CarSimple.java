 package com.qa.demo.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class CarSimple {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String model;

	private int wheels;

	private String make;

	
	public CarSimple() {
		super();
	}
	
	public CarSimple(String make) {
		this.make = make;
	}
	
	public CarSimple(String model, int wheels, String make) {
		super();
		this.model = model;
		this.wheels = wheels;
		this.make = make;
	}
	
	public CarSimple(Long id, String model, int wheels, String make) {
		super();
		this.id = id;
		this.model = model;
		this.wheels = wheels;
		this.make = make;
	}
	
	public CarSimple get() {
		return this;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}
}
