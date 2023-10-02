package com.example.carSalesGarage.service.impl;

import com.example.carSalesGarage.repository.CarRepository;
import com.example.carSalesGarage.domain.Car;
import com.example.carSalesGarage.domain.enumeration.FuelType;
import com.example.carSalesGarage.service.CarService;
import com.example.carSalesGarage.service.dto.CarDTO;
import com.example.carSalesGarage.service.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    public CarDTO addCar(CarDTO carDTO) {
        //check if registration date is after 2015
        if (carDTO.getRegistrationDate().isBefore(LocalDateTime.of(2015, 1, 1,0,0))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cars registered before 2015 are not allowed.");
        }
        Car car = carMapper.toEntity(carDTO);
        return carMapper.toDto(carRepository.save(car));
    }

    @Override
    public List<CarDTO> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, double maxPrice) {
        return carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice)
                .stream().map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllMakes() {
        return carRepository.findDistinctMake();
    }

    @Override
    public CarDTO updateCarPicture(Long carId, String picture) {
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car not found with ID: " + carId));
        existingCar.setPicture(picture);
        return carMapper.toDto(carRepository.save(existingCar));
    }
}
