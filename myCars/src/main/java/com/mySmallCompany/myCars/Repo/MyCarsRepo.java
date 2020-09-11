package com.mySmallCompany.myCars.Repo;

import com.mySmallCompany.myCars.Model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCarsRepo extends CrudRepository<Car, String> {
}
