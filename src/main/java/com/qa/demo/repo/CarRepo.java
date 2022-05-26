package com.qa.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.demo.dao.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
//	@Query(value = "SELECT * FROM Car WHERE model = ?1", nativeQuery = true)
	@Query("SELECT car from Car car WHERE car.model = ?1")
	public List<Car> readModel(String model);
	
	@Query(value = "SELECT * FROM Car WHERE make = ?1", nativeQuery = true)
	public List<Car> readMake(String make);
	
	@Query(value = "SELECT * FROM Car WHERE id = ?1", nativeQuery = true)
	public List<Car> readId(Long id);
}
