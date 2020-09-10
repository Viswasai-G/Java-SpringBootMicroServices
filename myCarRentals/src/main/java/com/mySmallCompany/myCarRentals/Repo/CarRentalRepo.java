package com.mySmallCompany.myCarRentals.Repo;

import com.mySmallCompany.myCarRentals.Model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalRepo extends CrudRepository<Car, String> {
}
