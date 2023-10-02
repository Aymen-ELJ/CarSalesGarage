package com.example.carSalesGarage.service.dto;

import com.example.carSalesGarage.domain.enumeration.FuelType;
import com.example.carSalesGarage.domain.enumeration.TransmissionType;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class CarDTO {

    @Schema(description = "The unique ID of the car")
    private Long id;

    @Schema(description = "The make of the car", required = true)
    @NotNull
    private String make;

    @Schema(description = "The model of the car", required = true)
    @NotNull
    private String model;

    @Schema(description = "The registration date of the car", required = true)
    @NotNull
    private LocalDateTime registrationDate;

    @Schema(description = "The price of the car", required = true)
    @NotNull
    private double price;

    @Schema(description = "The fuel type of the car", allowableValues = "DIESEL, ELECTRIC, HYBRID", required = true)
    @NotNull
    private FuelType fuelType;

    @Schema(description = "The mile age of the car", required = true)
    @NotNull
    private double mileage;

    @Schema(description = "The transmission type of the car", allowableValues = "MANUAL, SEMI_AUTOMATIC, AUTOMATIC", required = true)
    @NotNull
    private TransmissionType transmissionType;

    @Schema(description = "The picture of the car")
    private String picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Double.compare(carDTO.price, price) == 0 && Double.compare(carDTO.mileage, mileage) == 0 && Objects.equals(id, carDTO.id) && Objects.equals(make, carDTO.make) && Objects.equals(model, carDTO.model) && Objects.equals(registrationDate, carDTO.registrationDate) && fuelType == carDTO.fuelType && transmissionType == carDTO.transmissionType && Objects.equals(picture, carDTO.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, registrationDate, price, fuelType, mileage, transmissionType, picture);
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", registrationDate=" + registrationDate +
                ", price=" + price +
                ", fuelType=" + fuelType +
                ", mileage=" + mileage +
                ", transmissionType=" + transmissionType +
                ", picture='" + picture + '\'' +
                '}';
    }
}
