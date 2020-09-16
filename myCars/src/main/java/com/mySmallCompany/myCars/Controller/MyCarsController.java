package com.mySmallCompany.myCars.Controller;

import com.mySmallCompany.myCars.Model.CarMakeModel;
import com.mySmallCompany.myCars.Service.MyCarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyCarsController {

    @Autowired
    private MyCarsService myCarsService;

    @GetMapping(value = "/getFirstAvailable")
    public CarMakeModel getFirstAvailableCar(){
        return myCarsService.getFirstAvailableCar();
    }

    @GetMapping(value = "/getInventory")
    public List<CarMakeModel> getInventory() {
        return myCarsService.getInventory();
    }

    @GetMapping(value = "/getCar/{vin}")
    CarMakeModel getCar(@PathVariable(value = "vin") String vin) {
        return myCarsService.getCar(vin);
    }

    @PostMapping(value = "/addCar")
    public CarMakeModel AddCarToInventory(@RequestBody CarMakeModel car){
        return myCarsService.AddCarToInventory(car);
    }

    @DeleteMapping(value = "/removeCar/{vin}")
    public void removeCarFromInventory(@PathVariable(value = "vin") String vin){
        myCarsService.removeCarFromInventory(vin);
    }

    @GetMapping(value = "/getAllCarsInUse")
    public List<CarMakeModel> getAllCarsInUse() {
        return myCarsService.getAllCarsInUse();
    }

}
