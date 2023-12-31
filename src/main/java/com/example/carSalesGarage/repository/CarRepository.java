package com.example.carSalesGarage.repository;

import com.example.carSalesGarage.domain.Car;
import com.example.carSalesGarage.domain.enumeration.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


    List<Car> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, double maxPrice);
    @Query("SELECT DISTINCT c.make FROM Car c")
    List<String> findDistinctMake();
}
