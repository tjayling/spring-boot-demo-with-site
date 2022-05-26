package com.qa.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.qa.demo.exception.CarException;
import com.qa.demo.repo.CarRepo;
import com.qa.demo.service.CarService;

@RestController()
@RequestMapping("/car")
@CrossOrigin
public class CarController {
	
	private CarService service;
	
	private CarRepo repo;

	@Autowired
	public CarController(CarService service, CarRepo repo) {
		this.service = service;
		this.repo = repo;
	}

	@PostMapping("/create")
	public ResponseEntity<CarDto> create(@RequestBody Car car) {
		return new ResponseEntity<CarDto>(this.service.create(car), HttpStatus.CREATED);
	}
	
	@PostMapping("/createMany")
	public ResponseEntity<List<CarDto>> createMany(@RequestBody List<Car> cars) {
		return new ResponseEntity<List<CarDto>>(this.service.createMany(cars), HttpStatus.CREATED);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<CarDto>> readAll() {
		return new ResponseEntity<List<CarDto>>(this.service.readAll(), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<CarDto> update(@RequestBody Car newCar) {
		return new ResponseEntity<CarDto>(this.service.update(newCar), HttpStatus.ACCEPTED);
//		Optional<Car> car = this.service.update(newCar);
//		return car.isPresent() ? new ResponseEntity<Car>(car.get(), HttpStatus.ACCEPTED)
//				: new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CarDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/readBy/id:{id}")
	public ResponseEntity<Car> readId(@PathVariable Long id) {
		Optional<Car> car = this.service.readId(id);
		return car.isPresent() ? new ResponseEntity<Car>(car.get(), HttpStatus.OK)
				: new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
//		return new ResponseEntity<Car>(this.service.readId(id).get(), HttpStatus.OK);
	}

	@GetMapping("/readBy/model:{model}")
	public ResponseEntity<List<Car>> readModel(@PathVariable String model) {
		List<Car> cars = this.service.readModel(model);
		return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
	}
	
	@GetMapping("/readBy/make:{make}")
	public ResponseEntity<List<Car>> readMake(@PathVariable String make) throws CarException {
		return this.repo.readMake(make).stream().map(n -> {
			try {
				return n.orElseThrow(CarException::new);
			} catch(CarException e) {
					e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList()).stream().map(this::mapToDto).collect(Collectors.toList());
		List<Car> cars = this.service.readMake(make);
		return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
	}

}
