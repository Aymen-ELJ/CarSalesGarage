package com.example.carSalesGarage.service.mapper;

public interface EntityMapper<D, E>{

    E toEntity(D dto);

    D toDto(E entity);

}
