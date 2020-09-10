package com.mySmallCompany.myCarRentals.Service;


import com.mySmallCompany.myCarRentals.Exception.CarNotFoundException;
import com.mySmallCompany.myCarRentals.Exception.CarNotificationAlertException;
import com.mySmallCompany.myCarRentals.Model.Car;
import com.mySmallCompany.myCarRentals.Model.Tires;
import com.mySmallCompany.myCarRentals.Repo.CarRentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarRentalServiceImpl implements CarRentalService {

    @Autowired
    private CarRentalRepo carRentalRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Car> getAllCars() {
        return (List<Car>) carRentalRepo.findAll();
    }

    @Override
    public Car getCar(String vin) {
        Optional<Car> carOptional = carRentalRepo.findById(vin);
        if(carOptional.isPresent()){
            return carOptional.get();
        }
        else{
            throw new CarNotFoundException("Car not found!");
        }
    }

    @Override
    public void addNewCar(Car car) {
        try{
            if(car.isCheckEngineLightOn()){
                restTemplate.postForObject("http://localhost:8081/addNotification/CHECK_ENGINE", car, Car.class);
            }
            if(car.getSpeed()>70){
                restTemplate.postForObject("http://localhost:8081/addNotification/HIGH_SPEED", car, Car.class);
            }
        } catch (RestClientException e) {
            throw new CarNotificationAlertException("Failed to post notification to Notification alert service:"+e.getMessage());
        }

        finally {
            carRentalRepo.save(car);
        }

    }

    @Override
    public List<Double> getCarLocation(String vin) {
        Optional<Car> carOptional = carRentalRepo.findById(vin);
        if(!carOptional.isPresent()){
            throw new CarNotFoundException("Car not found!");
        }
        List<Double> carLocation = new ArrayList<>();
        carLocation.add(carOptional.get().getLatitude()); carLocation.add(carOptional.get().getLongitude());
        return carLocation;
    }

    @Override
    public boolean carNeedsService(String vin) {
        Optional<Car> carOptional = carRentalRepo.findById(vin);
        if(!carOptional.isPresent()){
            throw new CarNotFoundException("Car not found!");
        }
        return carOptional.get().isCheckEngineLightOn() || carOptional.get().isEngineCoolantLow();
    }

    @Override
    public Tires getTirePressures(String vin) {
        Optional<Car> carOptional = carRentalRepo.findById(vin);
        if(!carOptional.isPresent()){
            throw new CarNotFoundException("Car not found!");
        }
        return carOptional.get().getTires();
    }


}
