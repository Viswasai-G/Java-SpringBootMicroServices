package com.mySmallCompany.myCarRentalAlerts.Service;

import com.mySmallCompany.myCarRentalAlerts.Model.CarReading;
import com.mySmallCompany.myCarRentalAlerts.Model.CarRentalAlert;

import java.util.List;
import java.util.Optional;

public interface carRentalAlertService {
    List<CarRentalAlert> getAllNotifications();

    Optional getNotification(String id);

    void addNotification(CarRentalAlert carNotificationAlert);

    List<String> getIssueRelated(String issue);

    void issueResolved(String id);

    CarReading getCarDetails(String vin);
}
