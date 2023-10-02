package com.example.carSalesGarage.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

public class AvailableMakesDTO {
    @Schema(description = "All available makes")
    List<String> availableMakes;

    public List<String> getAvailableMakes() {
        return availableMakes;
    }

    public void setAvailableMakes(List<String> availableMakes) {
        this.availableMakes = availableMakes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableMakesDTO that = (AvailableMakesDTO) o;
        return Objects.equals(availableMakes, that.availableMakes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableMakes);
    }

    @Override
    public String toString() {
        return "AvailableMakesDTO{" +
                "availableMakes=" + availableMakes +
                '}';
    }
}
