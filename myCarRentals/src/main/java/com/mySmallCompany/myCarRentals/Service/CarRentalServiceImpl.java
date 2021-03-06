package com.mySmallCompany.myCarRentals.Service;


import com.mySmallCompany.myCarRentals.AWS.MyCarSqsSender;
import com.mySmallCompany.myCarRentals.Exception.CarNotFoundException;
import com.mySmallCompany.myCarRentals.Exception.CarNotificationAlertException;
import com.mySmallCompany.myCarRentals.Exception.MyCarsServiceException;
import com.mySmallCompany.myCarRentals.Model.CarMakeModel;
import com.mySmallCompany.myCarRentals.Model.CarReading;
import com.mySmallCompany.myCarRentals.Model.TireReading;
import com.mySmallCompany.myCarRentals.Repo.CarRentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarRentalServiceImpl implements CarRentalService {

    @Autowired
    private MyCarSqsSender myCarSqsSender;

    @Autowired
    private CarRentalRepo carRentalRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("myCarsRestTemplate")
    private RestTemplate myCarsRestTemplate;

    @Override
    public List<CarReading> getAllCars() {
        return (List<CarReading>) carRentalRepo.findAll();
    }

    @Override
    public CarReading getCar(String vin) {
        Optional<CarReading> carOptional = carRentalRepo.findById(vin);
        if(carOptional.isPresent()){
            return carOptional.get();
        }
        else{
            throw new CarNotFoundException("Car not found!");
        }
    }

    @Override
    public void addNewCar(CarReading car) {
        try{
            if(car.isCheckEngineLightOn()){
                /**Lets say we only want to send an alert if the make of the model is not Honda
                 * TO-DO: Make call async
                 * Use CompletableFuture<CarMakeModel> and apply condition async*/
                try {
                    String make = myCarsRestTemplate.getForEntity("http://mycars/getCar/"+car.getVin(), CarMakeModel.class)
                            .getBody()
                            .getMake();
                    if(make.equalsIgnoreCase("honda")){
                        restTemplate.postForObject("http://mycarrentalalerts/addNotification/CHECK_ENGINE", car, CarReading.class);
                    }
                }
                catch (Exception e){
                    throw new MyCarsServiceException("Problem in getting carMakemodel!");
                }
            }
            if(car.getSpeed()>70){
                /**
                Send Car object to SQS queue
                The notification sent contains only the car object but does not contain the Issue.
                When polling messages from the queue, RentalAlerts sets the issue to LOW_FUEL.

                 TO-DO: Send a alert object rather than car object
                */
//                myCarSqsSender.send(car);
                restTemplate.postForObject("http://mycarrentalalerts/addNotification/HIGH_SPEED", car, CarReading.class);
            }
            if(car.getFuelVolume()<3){
                myCarSqsSender.send(car);
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
        Optional<CarReading> carOptional = carRentalRepo.findById(vin);
        if(!carOptional.isPresent()){
            throw new CarNotFoundException("Car not found!");
        }
        List<Double> carLocation = new ArrayList<>();
        carLocation.add(carOptional.get().getLatitude()); carLocation.add(carOptional.get().getLongitude());
        return carLocation;
    }

    @Override
    public boolean carNeedsService(String vin) {
        Optional<CarReading> carOptional = carRentalRepo.findById(vin);
        if(!carOptional.isPresent()){
            throw new CarNotFoundException("Car not found!");
        }
        return carOptional.get().isCheckEngineLightOn() || carOptional.get().isEngineCoolantLow();
    }

    @Override
    public TireReading getTirePressures(String vin) {
        Optional<CarReading> carOptional = carRentalRepo.findById(vin);
        if(!carOptional.isPresent()){
            throw new CarNotFoundException("Car not found!");
        }
        return carOptional.get().getTires();
    }


}
