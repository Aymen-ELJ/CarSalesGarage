package com.example.carSalesGarage.repository;

import com.example.carSalesGarage.domain.RequestTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTrackingRepository extends JpaRepository<RequestTracking, Long> {
}
