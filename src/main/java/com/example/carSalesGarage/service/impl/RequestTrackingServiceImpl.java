package com.example.carSalesGarage.service.impl;

import com.example.carSalesGarage.repository.RequestTrackingRepository;
import com.example.carSalesGarage.domain.RequestTracking;
import com.example.carSalesGarage.service.RequestTrackingService;
import com.example.carSalesGarage.service.dto.RequestTrackingDTO;
import com.example.carSalesGarage.service.mapper.RequestTrackingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestTrackingServiceImpl implements RequestTrackingService {

    @Autowired
    private RequestTrackingRepository requestTrackingRepository;

    @Autowired
    private RequestTrackingMapper requestTrackingMapper;

    @Override
    public RequestTrackingDTO save(RequestTrackingDTO requestTrackingDTO){
        RequestTracking requestTracking = requestTrackingMapper.toEntity(requestTrackingDTO);
        return requestTrackingMapper.toDto( requestTrackingRepository.save(requestTracking));
    }

}
