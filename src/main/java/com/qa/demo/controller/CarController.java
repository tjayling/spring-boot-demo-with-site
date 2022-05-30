package com.qa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.dao.Car;
import com.qa.demo.dto.CarDto;
import com.qa.demo.service.CarService;

@RestController()
@RequestMapping("/car")
@CrossOrigin
public class CarController {

	private CarService service;

	@Autowired
	public CarController(CarService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<CarDto> create(@RequestBody Car car) {
		return new ResponseEntity<>(this.service.create(car), HttpStatus.CREATED);
	}

	@PostMapping("/createMany")
	public ResponseEntity<List<CarDto>> createMany(@RequestBody List<Car> cars) {
		return new ResponseEntity<>(this.service.createMany(cars), HttpStatus.CREATED);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<CarDto>> readAll() {
		return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<CarDto> update(@RequestBody Car newCar) {
		return new ResponseEntity<>(this.service.update(newCar), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/readBy/id:{id}")
	public ResponseEntity<List<CarDto>> readId(@PathVariable Long id) {
		List<CarDto> car = this.service.readId(id);
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@GetMapping("/readBy/model:{model}")
	public ResponseEntity<List<CarDto>> readModel(@PathVariable String model) {
		List<CarDto> cars = this.service.readModel(model);
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}

	@GetMapping("/readBy/make:{make}")
	public ResponseEntity<List<CarDto>> readMake(@PathVariable String make) {
		List<CarDto> cars = this.service.readMake(make);
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}

}
