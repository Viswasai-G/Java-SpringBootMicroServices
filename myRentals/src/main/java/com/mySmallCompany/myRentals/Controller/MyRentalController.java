package com.mySmallCompany.myRentals.Controller;


import com.mySmallCompany.myRentals.Model.Car;
import com.mySmallCompany.myRentals.Model.Rental;
import com.mySmallCompany.myRentals.Model.User;
import com.mySmallCompany.myRentals.Service.MyRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyRentalController {

    @Autowired
    private MyRentalService myRentalService;

    @GetMapping(value = "/getRental={id}")
    public Rental getRental(@PathVariable(value = "id") String id){
        return myRentalService.getRental(id);
    }

    @GetMapping(value = "/getUserDetails/{id}")
    public User getUserDetails(@PathVariable(value = "id") String id){
        return myRentalService.getUserDetails(id);
    }

    @GetMapping(value = "/getCarDetails/{id}")
    public Car getCarDetails(@PathVariable(value = "id") String id){
        return myRentalService.getCarDetails(id);
    }

    @GetMapping(value = "/getAllRentals")
    public List<Rental> getAllRentals(){
        return myRentalService.getAllRentals();
    }

    @PostMapping(value = "/createRental")
    public Rental createRental(@RequestBody Rental rental){
         return myRentalService.createRental(rental);
    }

    @PutMapping(value = "/updateRental/{id}")
    public Rental updateRental(@RequestBody Rental rental,@PathVariable(value = "id") String id){
        return myRentalService.updateRental(rental, id);
    }

    @DeleteMapping(value = "/deleteRental/{id}")
    public void deleteRental(@PathVariable(value = "id") String id){
        myRentalService.deleteRental(id);
    }

}
