package com.mySmallCompany.myCarRentals.Service;

import com.mySmallCompany.myCarRentals.Model.CarReading;
import com.mySmallCompany.myCarRentals.Model.TireReading;

import java.util.List;

public interface CarRentalService {

    List<CarReading> getAllCars();

    CarReading getCar(String vin);

    void addNewCar(CarReading car);

    List<Double> getCarLocation(String vin);

    boolean carNeedsService(String vin);

    TireReading getTirePressures(String vin);

}
