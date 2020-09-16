package com.mySmallCompany.myCars.Service;

import com.mySmallCompany.myCars.Exception.CarNotFoundException;
import com.mySmallCompany.myCars.Exception.NoCarAvailable;
import com.mySmallCompany.myCars.Model.CarMakeModel;
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
    public CarMakeModel getFirstAvailableCar() {
        Optional<CarMakeModel> carOptional = getInventory().stream().filter(car -> !car.isInUse()).findFirst();
        if(carOptional.isPresent()){
            carOptional.get().setInUse(true);
            myCarsRepo.save(carOptional.get());
            return carOptional.get();
        }
        throw new NoCarAvailable("All cars are in use!");
    }

    @Override
    public List<CarMakeModel> getInventory() {
        return (List<CarMakeModel>) myCarsRepo.findAll();
    }

    @Override
    public CarMakeModel getCar(String vin) {
        Optional<CarMakeModel> carOptional = myCarsRepo.findById(vin);
        if(carOptional.isPresent()){
            return carOptional.get();
        }
        throw new CarNotFoundException("Car Not Found!");
    }

    @Override
    public CarMakeModel AddCarToInventory(CarMakeModel car) {
        return myCarsRepo.save(car);
    }

    @Override
    public void removeCarFromInventory(String vin) {
        myCarsRepo.deleteById(vin);
    }

    @Override
    public List<CarMakeModel> getAllCarsInUse() {
        List<CarMakeModel> carsInUse = (List<CarMakeModel>) myCarsRepo.findAll();
        return carsInUse.stream().filter(car -> car.isInUse()).collect(Collectors.toList());
    }
}
