package com.qa.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.qa.demo.dao.Car;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverDto {
	private Long id;
	private String firstName;
	private String lastName;
	private List<Car> cars = new ArrayList<>();
}