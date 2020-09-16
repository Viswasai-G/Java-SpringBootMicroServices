package com.mySmallCompany.myCarRentals.Controller;

import com.mySmallCompany.myCarRentals.Model.CarReading;
import com.mySmallCompany.myCarRentals.Model.TireReading;
import com.mySmallCompany.myCarRentals.Service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarRentalController {

    @Autowired
    CarRentalService carRentalService;

    @GetMapping(value = "/getAllCars")
    public List<CarReading> getAllCars(){
        return carRentalService.getAllCars();
    }

    @GetMapping(value = "/getCar/{vin}")
    public CarReading getCar(@PathVariable(value = "vin")String vin){
        return carRentalService.getCar(vin);
    }

    @PostMapping(value = "/addCar")
    public void addNewCar(@RequestBody CarReading car){
        carRentalService.addNewCar(car);
    }

    @GetMapping(value = "/getCarLocation/{vin}")
    public List<Double> getCarLocation(@PathVariable(value = "vin")String vin){
        return carRentalService.getCarLocation(vin);
    }

    @GetMapping(value = "/carNeedsService/{vin}")
    public boolean carNeedsService(@PathVariable(value = "vin")String vin){
        return carRentalService.carNeedsService(vin);
    }

    @GetMapping(value = "/getCar/getTires/{vin}")
    public TireReading getTirePressures(@PathVariable(value = "vin")String vin){
        return carRentalService.getTirePressures(vin);
    }

}
