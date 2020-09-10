package com.mySmallCompany.myRentals.Service;

import com.mySmallCompany.myRentals.Exception.RentalNotFoundException;
import com.mySmallCompany.myRentals.Model.Car;
import com.mySmallCompany.myRentals.Model.Rental;
import com.mySmallCompany.myRentals.Model.User;
import com.mySmallCompany.myRentals.Repo.MyRentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyRentalServiceImpl implements MyRentalService {

    @Autowired
    private MyRentalRepo myRentalRepo;

    @Override
    public Rental getRental(String id) {
        Optional<Rental> rentalOptional = myRentalRepo.findById(id);
        if(rentalOptional.isPresent()){
            return rentalOptional.get();
        }
        else{
            throw new RentalNotFoundException("Rental Not Found!");
        }
    }

    @Override
    public User getUserDetails(String id) {
        Optional<Rental> rental = myRentalRepo.findById(id);
        if(rental.isPresent()){
            return rental.get().getUser();
        }
        else{
            throw new RentalNotFoundException("Rental Not found!");
        }
    }

    @Override
    public Car getCarDetails(String id) {
        Optional<Rental> rental = myRentalRepo.findById(id);
        if(rental.isPresent()){
            return rental.get().getCar();
        }
        else{
            throw new RentalNotFoundException("Rental Not found!");
        }
    }

    @Override
    public List<Rental> getAllRentals() {
        return (List<Rental>) myRentalRepo.findAll();
    }

    @Override
    public Rental createRental(Rental rental) {
        return myRentalRepo.save(rental);
    }

    @Override
    public Rental updateRental(Rental rental, String id) {
        Optional<Rental> prevRental = myRentalRepo.findById(id);
        if(prevRental.isPresent()){
            myRentalRepo.delete(prevRental.get());
            return myRentalRepo.save(rental);
        }
        else{
            throw new RentalNotFoundException("Rental Not found!");
        }
    }

    @Override
    public void deleteRental(String id) {
        try{
            myRentalRepo.deleteById(id);
        }
        catch (Exception e){
            throw new RentalNotFoundException(e.getMessage());
        }
    }
}
