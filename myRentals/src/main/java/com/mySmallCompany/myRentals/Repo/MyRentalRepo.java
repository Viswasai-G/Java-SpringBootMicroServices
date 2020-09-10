package com.mySmallCompany.myRentals.Repo;

import com.mySmallCompany.myRentals.Model.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRentalRepo extends CrudRepository<Rental, String> {
}
