package com.mySmallCompany.myCarRentals.Service;

import com.mySmallCompany.myCarRentals.Model.Car;
import com.mySmallCompany.myCarRentals.Model.Tires;

import java.util.List;
import java.util.Optional;

public interface CarRentalService {

    List<Car> getAllCars();

    Car getCar(String vin);

    void addNewCar(Car car);

    List<Double> getCarLocation(String vin);

    boolean carNeedsService(String vin);

    Tires getTirePressures(String vin);

}
