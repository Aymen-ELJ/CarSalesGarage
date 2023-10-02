package com.example.carSalesGarage.service.mapper;

import com.example.carSalesGarage.domain.Car;
import com.example.carSalesGarage.service.dto.CarDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CarMapper extends EntityMapper<CarDTO, Car>{

}
