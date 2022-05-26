package com.qa.demo.dto;

public class CarDto {

	private long id;

	private String model;

	private int wheels;

	private String make;

//	private String plateNumber;

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

//	public String getPlateNumber() {
//		return plateNumber;
//	}
//
//	public void setPlateNumber(String plateNumber) {
//		this.plateNumber = plateNumber;
//	}

}