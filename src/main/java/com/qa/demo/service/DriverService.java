package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.demo.dao.Driver;
import com.qa.demo.dto.DriverDto;
import com.qa.demo.repo.DriverRepo;

@Service
public class DriverService {

	private ModelMapper mapper;

	DriverRepo repo;

	public DriverService(DriverRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private DriverDto mapToDto(Driver driver) {
		return this.mapper.map(driver, DriverDto.class);
	}

	public DriverDto create(Driver driver) {
		return this.mapToDto(this.repo.save(driver));
	}

	public List<DriverDto> createMany(List<Driver> drivers) {
		return this.repo.saveAll(drivers).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<DriverDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public DriverDto update(Driver newDriver) {
		Driver driver = this.repo.findById(newDriver.getId()).get();
		driver.setFirstName(newDriver.getFirstName());
		driver.setLastName(newDriver.getLastName());

		return this.mapToDto(this.repo.save(driver));
	}

	public Boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public List<DriverDto> readId(Long id) {
		return this.repo.readId(id).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<DriverDto> readFirstName(String model) {
		return this.repo.readFirstName(model).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<DriverDto> readLastName(String make) {
		return this.repo.readLastName(make).stream().map(this::mapToDto).collect(Collectors.toList());
	}
}
