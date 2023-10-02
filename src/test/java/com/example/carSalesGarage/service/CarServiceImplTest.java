package com.example.carSalesGarage.service;

import com.example.carSalesGarage.repository.CarRepository;
import com.example.carSalesGarage.domain.Car;
import com.example.carSalesGarage.domain.enumeration.FuelType;
import com.example.carSalesGarage.domain.enumeration.TransmissionType;
import com.example.carSalesGarage.service.dto.CarDTO;
import com.example.carSalesGarage.service.impl.CarServiceImpl;
import com.example.carSalesGarage.service.mapper.CarMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carServiceUnderTest;
    @Mock
    private CarMapper carMapper;
    @Mock
    private CarRepository carRepository;


    @Test
    void testAddCar() {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setMake("HYUNDAI");
        carDTO.setModel("i10");
        carDTO.setRegistrationDate(LocalDateTime.now());
        carDTO.setPrice(15000.0);
        carDTO.setFuelType(FuelType.DIESEL);
        carDTO.setMileage(2000.0);
        carDTO.setTransmissionType(TransmissionType.MANUAL);
        carDTO.setPicture("picture-url");

        Car carEntity = new Car();
        carEntity.setId(1L);
        carEntity.setMake("HYUNDAI");
        carEntity.setModel("i10");
        carEntity.setRegistrationDate(LocalDateTime.now());
        carEntity.setPrice(15000.0);
        carEntity.setFuelType(FuelType.DIESEL);
        carEntity.setMileage(2000.0);
        carEntity.setTransmissionType(TransmissionType.MANUAL);
        carEntity.setPicture("picture-url");

        Car savedCarEntity = new Car();
        savedCarEntity.setId(1L);
        savedCarEntity.setMake("HYUNDAI");
        savedCarEntity.setModel("i10");
        savedCarEntity.setRegistrationDate(LocalDateTime.now());
        savedCarEntity.setPrice(15000.0);
        savedCarEntity.setFuelType(FuelType.DIESEL);
        savedCarEntity.setMileage(2000.0);
        savedCarEntity.setTransmissionType(TransmissionType.MANUAL);
        savedCarEntity.setPicture("picture-url");

        when(carMapper.toEntity(carDTO)).thenReturn(carEntity);
        when(carRepository.save(carEntity)).thenReturn(savedCarEntity);
        when(carMapper.toDto(savedCarEntity)).thenReturn(carDTO);

        CarDTO result =  carServiceUnderTest.addCar(carDTO);

        assertNotNull(result);
        assertEquals(carDTO, result);
    }

    @Test
    void testAddCarWithInvalidRegistrationDate() {
        LocalDateTime registrationDate = LocalDateTime.of(2014, 1, 1, 0, 0);
        CarDTO carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setMake("HYUNDAI");
        carDTO.setModel("i10");
        carDTO.setRegistrationDate(registrationDate);
        carDTO.setPrice(15000.0);
        carDTO.setFuelType(FuelType.DIESEL);
        carDTO.setMileage(2000.0);
        carDTO.setTransmissionType(TransmissionType.MANUAL);
        carDTO.setPicture("picture-url");


        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            carServiceUnderTest.addCar(carDTO);
        });

        assertEquals("Cars registered before 2015 are not allowed.", exception.getReason());
    }


    @Test
    void testGetCarsByFuelTypeAndMaxPrice() {
        FuelType fuelType = FuelType.DIESEL;
        double maxPrice = 45000.0;

        Car car1 = new Car();
        car1.setId(1L);
        car1.setFuelType(fuelType);
        car1.setPrice(40000.0);
        car1.setMake("HYUNDAI");
        car1.setModel("i10");
        car1.setRegistrationDate(LocalDateTime.now());
        car1.setMileage(2000.0);
        car1.setTransmissionType(TransmissionType.MANUAL);
        car1.setPicture("picture-url");

        Car car2 = new Car();
        car2.setId(2L);
        car2.setFuelType(fuelType);
        car2.setPrice(50000.0);
        car2.setMake("HYUNDAI");
        car2.setModel("i10");
        car2.setRegistrationDate(LocalDateTime.now());
        car2.setMileage(2000.0);
        car2.setTransmissionType(TransmissionType.MANUAL);
        car2.setPicture("picture-url");

        CarDTO carDto1 = new CarDTO();
        carDto1.setId(1L);
        carDto1.setFuelType(fuelType);
        carDto1.setPrice(40000.0);
        carDto1.setMake("HYUNDAI");
        carDto1.setModel("i10");
        carDto1.setRegistrationDate(LocalDateTime.now());
        carDto1.setMileage(2000.0);
        carDto1.setTransmissionType(TransmissionType.MANUAL);
        carDto1.setPicture("picture-url");

        CarDTO carDto2 = new CarDTO();
        carDto2.setId(2L);
        carDto2.setFuelType(fuelType);
        carDto2.setPrice(50000.0);
        carDto2.setMake("HYUNDAI");
        carDto2.setModel("i10");
        carDto2.setRegistrationDate(LocalDateTime.now());
        carDto2.setMileage(2000.0);
        carDto2.setTransmissionType(TransmissionType.MANUAL);
        carDto2.setPicture("picture-url");

        when(carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice))
                .thenReturn(Arrays.asList(car1));
        when(carMapper.toDto(car1)).thenReturn(carDto1);

        List<CarDTO> result = carServiceUnderTest.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(FuelType.DIESEL, result.get(0).getFuelType());
        assertEquals(40000.0, result.get(0).getPrice());
    }

    @Test
    void testGetAllMakes() {
        List<String> expectedMakes = Arrays.asList("Toyota", "Honda", "Ford");

        when(carRepository.findDistinctMake()).thenReturn(expectedMakes);

        List<String> result = carServiceUnderTest.getAllMakes();

        assertEquals(expectedMakes.size(), result.size());
        assertEquals(expectedMakes, result);
    }

    @Test
    void testUpdateCarPicture() {
        Long carId = 1L;
        String newPicture = "new-picture-url";

        Car existingCar = new Car();
        existingCar.setId(carId);
        existingCar.setMake("HYUNDAI");
        existingCar.setModel("i10");
        existingCar.setRegistrationDate(LocalDateTime.now());
        existingCar.setPrice(15000.0);
        existingCar.setFuelType(FuelType.DIESEL);
        existingCar.setMileage(2000.0);
        existingCar.setTransmissionType(TransmissionType.MANUAL);
        existingCar.setPicture("old-picture-url");

        Car updatedCar = new Car();
        updatedCar.setId(carId);
        updatedCar.setMake("HYUNDAI");
        updatedCar.setModel("i10");
        updatedCar.setRegistrationDate(LocalDateTime.now());
        updatedCar.setPrice(15000.0);
        updatedCar.setFuelType(FuelType.DIESEL);
        updatedCar.setMileage(2000.0);
        updatedCar.setTransmissionType(TransmissionType.MANUAL);
        updatedCar.setPicture(newPicture);

        CarDTO updatedCarDto = new CarDTO();
        updatedCarDto.setId(carId);
        updatedCarDto.setMake("HYUNDAI");
        updatedCarDto.setModel("i10");
        updatedCarDto.setRegistrationDate(LocalDateTime.now());
        updatedCarDto.setPrice(15000.0);
        updatedCarDto.setFuelType(FuelType.DIESEL);
        updatedCarDto.setMileage(2000.0);
        updatedCarDto.setTransmissionType(TransmissionType.MANUAL);
        updatedCarDto.setPicture(newPicture);

        when(carRepository.findById(carId)).thenReturn(java.util.Optional.of(existingCar));
        doReturn(updatedCar).when(carRepository).save(existingCar);
        when(carMapper.toDto(updatedCar)).thenReturn(updatedCarDto);

        CarDTO result = carServiceUnderTest.updateCarPicture(carId, newPicture);

        assertEquals(updatedCarDto, result);
    }

    @Test
    void testUpdateCarPictureWhenCarNotFound() {
        Long carId = 1L;
        String newPicture = "new-picture-url";

        when(carRepository.findById(carId)).thenReturn(java.util.Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            carServiceUnderTest.updateCarPicture(carId, newPicture);
        });
    }
}
