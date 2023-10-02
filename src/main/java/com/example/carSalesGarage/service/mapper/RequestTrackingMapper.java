package com.example.carSalesGarage.service.mapper;

import com.example.carSalesGarage.domain.RequestTracking;
import com.example.carSalesGarage.service.dto.RequestTrackingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestTrackingMapper extends EntityMapper<RequestTrackingDTO, RequestTracking>{
}
