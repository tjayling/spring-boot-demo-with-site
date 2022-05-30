package com.qa.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {
	private Long id;
	private String make;
	private String model;
	private Integer cost;
//	private Driver driver;
}