package com.example.carSalesGarage.domain;

import com.example.carSalesGarage.domain.enumeration.FuelType;
import com.example.carSalesGarage.domain.enumeration.TransmissionType;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(description = "The unique ID of the car")
    private Long id;
    @Column(name = "make")
    @Schema(description = "The make of the car", required = true)
    @NotNull
    private String make;
    @Column(name = "model")
    @Schema(description = "The model of the car", required = true)
    @NotNull
    private String model;
    @Column(name = "registration_date")
    @Schema(description = "The registration date of the car", required = true)
    @NotNull
    private LocalDateTime registrationDate;
    @Column(name = "price")
    @Schema(description = "The price of the car", required = true)
    @NotNull
    private double price;
    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    @Schema(description = "The fuel type of the car", allowableValues = "DIESEL, ELECTRIC, HYBRID", required = true)
    @NotNull
    private FuelType fuelType;
    @Column(name = "mileage")
    @Schema(description = "The mile age of the car", required = true)
    @NotNull
    private double mileage;
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    @Schema(description = "The transmission type of the car", allowableValues = "MANUAL, SEMI_AUTOMATIC, AUTOMATIC", required = true)
    @NotNull
    private TransmissionType transmissionType;
    @Column(name = "picture")
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
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 && Double.compare(car.mileage, mileage) == 0 && Objects.equals(id, car.id) && Objects.equals(make, car.make) && Objects.equals(model, car.model) && Objects.equals(registrationDate, car.registrationDate) && fuelType == car.fuelType && transmissionType == car.transmissionType && Objects.equals(picture, car.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, registrationDate, price, fuelType, mileage, transmissionType, picture);
    }

    @Override
    public String toString() {
        return "Car{" +
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
