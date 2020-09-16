package com.mySmallCompany.myCarRentals.Repo;

import com.mySmallCompany.myCarRentals.Model.CarReading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalRepo extends CrudRepository<CarReading, String> {
}
