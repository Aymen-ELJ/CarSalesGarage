package com.example.carSalesGarage.service;


import com.example.carSalesGarage.domain.enumeration.FuelType;
import com.example.carSalesGarage.service.dto.CarDTO;

import java.util.List;
public interface CarService {

    CarDTO addCar(CarDTO car);
    List<CarDTO> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, double maxPrice);
    List<String> getAllMakes();
    CarDTO updateCarPicture(Long carId, String picture);
}
