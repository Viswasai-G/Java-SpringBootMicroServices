package com.mySmallCompany.myCars.Service;

import com.mySmallCompany.myCars.Model.Car;

import java.util.List;

public interface MyCarsService {

    Car getFirstAvailableCar();

    List<Car> getInventory();

    Car getCar(String vin);

    Car AddCarToInventory(Car car);

    void removeCarFromInventory(String vin);

    List<Car> getAllCarsInUse();

}
