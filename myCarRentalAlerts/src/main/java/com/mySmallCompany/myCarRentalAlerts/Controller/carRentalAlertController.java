package com.mySmallCompany.myCarRentalAlerts.Controller;

import com.mySmallCompany.myCarRentalAlerts.Model.CarReading;
import com.mySmallCompany.myCarRentalAlerts.Model.Issue;
import com.mySmallCompany.myCarRentalAlerts.Model.CarRentalAlert;
import com.mySmallCompany.myCarRentalAlerts.Service.carRentalAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class carRentalAlertController {

    @Autowired
    carRentalAlertService carRentalAlertService;


    @GetMapping(value = "/getAllNotif")
    List<CarRentalAlert> getAllNotifications(){
        return carRentalAlertService.getAllNotifications();
    }

    @GetMapping(value = "/getNotification/{id}")
    Optional getNotification(@PathVariable("id")String id){
        return carRentalAlertService.getNotification(id);
    }

    @PostMapping(value = "/addNotification/{issue}")
    void addNotification(@RequestBody CarReading carReading, @PathVariable(value = "issue") Issue issue){
        carRentalAlertService.addNotification(new CarRentalAlert(carReading.getVin(), issue));
    }

    @GetMapping(value = "/getIssue/{issue}")
    List<String> getIssueRelated(@PathVariable(value = "issue") String issue){
        return carRentalAlertService.getIssueRelated(issue);
    }

    @DeleteMapping(value = "/issueResolved/{id}")
    void issueResolved(@PathVariable(value = "id") String id){
        carRentalAlertService.issueResolved(id);
    }

    @GetMapping(value = "/getCarDetails/{vin}")
    CarReading getCarDetails(@PathVariable String vin){
        return carRentalAlertService.getCarDetails(vin);
    }

}
