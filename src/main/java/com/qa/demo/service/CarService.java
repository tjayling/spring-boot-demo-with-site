package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.demo.dao.Car;
import com.qa.demo.dto.CarDto;
import com.qa.demo.exception.CarException;
import com.qa.demo.repo.CarRepo;

@Service
public class CarService {

	private ModelMapper mapper;

	CarRepo repo;

	public CarService(CarRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private CarDto mapToDto(Car car) {
		return this.mapper.map(car, CarDto.class);
	}

	public CarDto create(Car car) {
//		return this.repo.save(car);
		return this.mapToDto(this.repo.save(car));
	}
	
	public List<CarDto> createMany(List<Car> cars) {
//		return this.repo.saveAll(cars);
		return this.repo.saveAll(cars).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<CarDto> readAll() {
//		return this.repo.findAll();
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public CarDto update(Car newCar) {
		Car car = this.repo.findById(newCar.getId()).get();
		car.setMake(newCar.getMake());
		car.setWheels(newCar.getWheels());
		car.setModel(newCar.getModel());
		car.setPlateNumber(newCar.getPlateNumber());

		return this.mapToDto(this.repo.save(car));
	}

	public Boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public List<CarDto> readId(Long id) {
		return this.repo.readId(id).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<CarDto> readModel(String model) {
		return this.repo.readModel(model).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<CarDto> readMake(String make) throws CarException {
//		return this.repo.readMake(make).stream().map(n -> {
//			try {
//				return n.orElseThrow(CarException::new);
//			} catch(CarException e) {
//					e.printStackTrace();
//			}
//			return null;
//		}).collect(Collectors.toList()).stream().map(this::mapToDto).collect(Collectors.toList());
		return this.repo.readMake(make).stream().map(this::mapToDto).collect(Collectors.toList());

	}
}
