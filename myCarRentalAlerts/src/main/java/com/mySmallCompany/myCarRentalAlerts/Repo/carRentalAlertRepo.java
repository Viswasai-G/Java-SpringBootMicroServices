package com.mySmallCompany.myCarRentalAlerts.Repo;

import com.mySmallCompany.myCarRentalAlerts.Model.CarRentalAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface carRentalAlertRepo extends JpaRepository<CarRentalAlert, String> {
}
