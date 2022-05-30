package com.qa.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.demo.dao.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Long> {
	@Query("SELECT driver FROM Driver driver WHERE driver.firstName = ?1")
	public List<Driver> readFirstName(String model);
	
	@Query(value = "SELECT * FROM Driver WHERE lastName = ?1", nativeQuery = true)
	public List<Driver> readLastName(String make);
	
	@Query(value = "SELECT * FROM Driver WHERE id = ?1", nativeQuery = true)
	public List<Driver> readId(Long id);
}
