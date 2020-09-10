package com.mySmallCompany.myCarRentalAlerts.Repo;

import com.mySmallCompany.myCarRentalAlerts.Model.carRentalAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface carRentalAlertRepo extends JpaRepository<carRentalAlert, String> {
}
