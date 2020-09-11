package com.mySmallCompany.myCars.Controller;

import com.mySmallCompany.myCars.Model.Car;
import com.mySmallCompany.myCars.Service.MyCarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyCarsController {

    @Autowired
    private MyCarsService myCarsService;

    @GetMapping(value = "/getFirstAvailable")
    public Car getFirstAvailableCar(){
        return myCarsService.getFirstAvailableCar();
    }

    @GetMapping(value = "/getInventory")
    public List<Car> getInventory() {
        return myCarsService.getInventory();
    }

    @GetMapping(value = "/getCar/{vin}")
    Car getCar(@PathVariable(value = "vin") String vin) {
        return myCarsService.getCar(vin);
    }

    @PostMapping(value = "/addCar")
    public Car AddCarToInventory(@RequestBody Car car){
        return myCarsService.AddCarToInventory(car);
    }

    @DeleteMapping(value = "/removeCar/{vin}")
    public void removeCarFromInventory(@PathVariable(value = "vin") String vin){
        myCarsService.removeCarFromInventory(vin);
    }

    @GetMapping(value = "/getAllCarsInUse")
    public List<Car> getAllCarsInUse() {
        return myCarsService.getAllCarsInUse();
    }

}
