package com.mySmallCompany.myCars.Service;

import com.mySmallCompany.myCars.Exception.CarNotFoundException;
import com.mySmallCompany.myCars.Exception.NoCarAvailable;
import com.mySmallCompany.myCars.Model.Car;
import com.mySmallCompany.myCars.Repo.MyCarsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyCarsServiceImpl implements MyCarsService {

    @Autowired
    private MyCarsRepo myCarsRepo;

    @Override
    public Car getFirstAvailableCar() {
        Optional<Car> carOptional = getInventory().stream().filter(car -> !car.isInUse()).findFirst();
        if(carOptional.isPresent()){
            carOptional.get().setInUse(true);
            myCarsRepo.save(carOptional.get());
            return carOptional.get();
        }
        throw new NoCarAvailable("All cars are in use!");
    }

    @Override
    public List<Car> getInventory() {
        return (List<Car>) myCarsRepo.findAll();
    }

    @Override
    public Car getCar(String vin) {
        Optional<Car> carOptional = myCarsRepo.findById(vin);
        if(carOptional.isPresent()){
            return carOptional.get();
        }
        throw new CarNotFoundException("Car Not Found!");
    }

    @Override
    public Car AddCarToInventory(Car car) {
        return myCarsRepo.save(car);
    }

    @Override
    public void removeCarFromInventory(String vin) {
        myCarsRepo.deleteById(vin);
    }

    @Override
    public List<Car> getAllCarsInUse() {
        List<Car> carsInUse = (List<Car>) myCarsRepo.findAll();
        return carsInUse.stream().filter(car -> car.isInUse()).collect(Collectors.toList());
    }
}
