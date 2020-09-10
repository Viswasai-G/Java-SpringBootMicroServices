package com.mySmallCompany.myCarRentals.Controller;

import com.mySmallCompany.myCarRentals.Model.Car;
import com.mySmallCompany.myCarRentals.Model.Tires;
import com.mySmallCompany.myCarRentals.Service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarRentalController {

    @Autowired
    CarRentalService carRentalService;

    @GetMapping(value = "/getAllCars")
    public List<Car> getAllCars(){
        return carRentalService.getAllCars();
    }

    @GetMapping(value = "/getCar/{vin}")
    public Car getCar(@PathVariable(value = "vin")String vin){
        return carRentalService.getCar(vin);
    }

    @PostMapping(value = "/addCar")
    public void addNewCar(@RequestBody Car car){
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
    public Tires getTirePressures(@PathVariable(value = "vin")String vin){
        return carRentalService.getTirePressures(vin);
    }

}
