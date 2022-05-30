package com.qa.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.demo.dao.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
//	@Query(value = "SELECT * FROM Car WHERE model = ?1", nativeQuery = true)
	@Query("SELECT car FROM Car car WHERE car.model = ?1")
	public List<Car> readModel(String model);
	
//	@Query(value = "SELECT FROM Car WHERE make = ?1", nativeQuery = true)
	@Query("SELECT car FROM Car car WHERE car.make = ?1")
	public List<Car> readMake(String make);
	
//	@Query(value = "SELECT * FROM Car WHERE id = ?1", nativeQuery = true)
	@Query("SELECT car FROM Car car WHERE car.id = ?1")
	public List<Car> readId(Long id);
	
	@Modifying
	@Query("DELETE FROM Car car WHERE car.id IN ?1")
	public void delete(List<Long> ids);
	
	@Query("SELECT car FROM Car car WHERE car.id in ?1")
	public List<Car> exists(List<Long> ids);
}
