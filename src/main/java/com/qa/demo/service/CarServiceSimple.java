package com.qa.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.demo.dao.CarSimple;

@Service
public class CarServiceSimple {

	List<CarSimple> CarSimples = new ArrayList<>(Arrays.asList(new CarSimple(1l, "mr3", 4, "Toyota"), new CarSimple(2l, "i8", 4, "BMW")));
	Long currentId = getCurrentId();

	public Long getCurrentId() {
		Long highest = 0l;
		for (CarSimple c : CarSimples) {
			if (c.getId() > highest) {
				highest = c.getId();
			}
		}
		return highest + 1;
	}

	// create
	public CarSimple create(CarSimple CarSimple) {
		CarSimple.setId(currentId);
		currentId++;
		this.CarSimples.add(CarSimple);
		return this.CarSimples.get(this.CarSimples.size() - 1);
//		return CarSimples.add(CarSimple) ? Optional.of(CarSimple) : Optional.empty();
	}

	// read
	public List<CarSimple> readAll() {
		return CarSimples;
	}

	// read id
	public Optional<CarSimple> readId(Long id) {
		for (CarSimple CarSimple : CarSimples) {
			if (CarSimple.getId() == id) {
				return Optional.of(CarSimple);
			}
		}
		return Optional.empty();
	}

	// update
	public Optional<CarSimple> update(Long id, CarSimple newCarSimple) throws RuntimeException {
		for (CarSimple CarSimple : this.CarSimples) {
			if (CarSimple.getId() == id) {
				this.CarSimples.remove(CarSimple);
				this.CarSimples.add(newCarSimple);
				return Optional.of(this.CarSimples.get(this.CarSimples.size() - 1));
			}
		}
		return Optional.empty();
	}

	// delete
	public Boolean delete(Long id) {
		for (CarSimple CarSimple : this.CarSimples) {
			if (CarSimple.getId() == id) {
				return this.CarSimples.remove(CarSimple);
			}
		}
		return false;
	}
}
