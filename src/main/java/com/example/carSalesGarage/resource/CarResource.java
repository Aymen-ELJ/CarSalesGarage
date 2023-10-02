package com.example.carSalesGarage.resource;

import com.example.carSalesGarage.domain.enumeration.FuelType;
import com.example.carSalesGarage.service.CarService;
import com.example.carSalesGarage.service.dto.AvailableMakesDTO;
import com.example.carSalesGarage.service.dto.CarDTO;
import com.example.carSalesGarage.service.mapper.CarMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car Resource", description = "Car management")
public class CarResource {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @PostMapping("/add")
    @Operation(summary = "add a car to the garage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal/Unhandled Exception")
    })
    public ResponseEntity<CarDTO> addCar(@ParameterObject @RequestBody @Valid CarDTO carDTO) {
        if(carDTO.getId() != null){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car can not already have an ID.");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CarDTO addedCar = carService.addCar(carDTO);
        return new ResponseEntity<>(addedCar, headers, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    @Operation(summary = "Get a list of cars by fuel type and max price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal/Unhandled Exception")
    })
    public ResponseEntity<List<CarDTO>> getCarListByFuelTypeAndMaxPrice(
            @Parameter(description = "the fuel Type",
                    schema = @Schema(allowableValues = {"DIESEL", "ELECTRIC", "HYBRID"}))   @RequestParam FuelType fuelType,
            @Parameter(description = "the max price")   @RequestParam double maxPrice) {
        List<CarDTO> cars = carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/makes")
    @Operation(summary = "Get a list of available makes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal/Unhandled Exception")
    })
    public ResponseEntity<AvailableMakesDTO> listAvailableMakes() {
        AvailableMakesDTO result = new AvailableMakesDTO();
        result.setAvailableMakes(carService.getAllMakes());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update-picture/{carId}")
    @Operation(summary = "update the car picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal/Unhandled Exception")
    })
    public ResponseEntity<CarDTO> updateCarPicture(
            @Parameter(name = "id", description = "car id")  @PathVariable Long carId,
            @Parameter(description = "the new picture") @RequestParam(required = true) String picture) {
        CarDTO updatedCar = carService.updateCarPicture(carId, picture);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

}
