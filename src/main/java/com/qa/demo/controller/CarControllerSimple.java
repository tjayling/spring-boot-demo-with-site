package com.qa.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qa.demo.dao.CarSimple;
import com.qa.demo.service.CarServiceSimple;

//@RestController()
//@RequestMapping("/CarSimple")
public class CarControllerSimple {

	private CarServiceSimple service;

	@Autowired
	public CarControllerSimple(CarServiceSimple service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<CarSimple> create(@RequestBody CarSimple CarSimple) {
		return new ResponseEntity<CarSimple>(this.service.create(CarSimple), HttpStatus.CREATED);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<CarSimple>> readAll() {
		return new ResponseEntity<List<CarSimple>>(this.service.readAll(), HttpStatus.OK);
	}

	@GetMapping("/readID/{id}")
	public ResponseEntity<CarSimple> readId(@PathVariable Long id) {
		Optional<CarSimple> CarSimple = this.service.readId(id);
		return CarSimple.isPresent() ? new ResponseEntity<CarSimple>(CarSimple.get(), HttpStatus.OK)
				: new ResponseEntity<CarSimple>(HttpStatus.NOT_FOUND);
//		return new ResponseEntity<CarSimple>(this.service.readId(id).get(), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CarSimple> update(@PathVariable Long id, @RequestBody CarSimple newCarSimple) {
		Optional<CarSimple> CarSimple = this.service.update(id, newCarSimple);
		return CarSimple.isPresent() ? new ResponseEntity<CarSimple>(CarSimple.get(), HttpStatus.ACCEPTED)
				: new ResponseEntity<CarSimple>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CarSimple> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		return new ResponseEntity<Boolean>(this.service.delete(id), HttpStatus.OK);
	}

}
