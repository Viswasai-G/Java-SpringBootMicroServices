package com.mySmallCompany.myCars.Repo;

import com.mySmallCompany.myCars.Model.CarMakeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCarsRepo extends CrudRepository<CarMakeModel, String> {
}
