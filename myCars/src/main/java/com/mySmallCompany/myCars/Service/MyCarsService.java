package com.mySmallCompany.myCars.Service;

import com.mySmallCompany.myCars.Model.CarMakeModel;

import java.util.List;

public interface MyCarsService {

    CarMakeModel getFirstAvailableCar();

    List<CarMakeModel> getInventory();

    CarMakeModel getCar(String vin);

    CarMakeModel AddCarToInventory(CarMakeModel car);

    void removeCarFromInventory(String vin);

    List<CarMakeModel> getAllCarsInUse();

}
