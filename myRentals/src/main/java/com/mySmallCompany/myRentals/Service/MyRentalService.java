package com.mySmallCompany.myRentals.Service;

import com.mySmallCompany.myRentals.Model.CarReading;
import com.mySmallCompany.myRentals.Model.Rental;
import com.mySmallCompany.myRentals.Model.User;

import java.util.List;

public interface MyRentalService {

    Rental getRental(String id);

    User getUserDetails(String id);

    CarReading getCarDetails(String id);

    List<Rental> getAllRentals();

    Rental createRental(Rental rental);

    Rental updateRental(Rental rental, String id);

    void deleteRental(String id);

}
