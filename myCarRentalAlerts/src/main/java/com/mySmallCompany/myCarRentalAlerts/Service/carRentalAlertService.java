package com.mySmallCompany.myCarRentalAlerts.Service;

import com.mySmallCompany.myCarRentalAlerts.Model.Car;
import com.mySmallCompany.myCarRentalAlerts.Model.carRentalAlert;

import java.util.List;
import java.util.Optional;

public interface carRentalAlertService {
    List<carRentalAlert> getAllNotifications();

    Optional getNotification(String id);

    void addNotification(carRentalAlert carNotificationAlert);

    List<String> getIssueRelated(String issue);

    void issueResolved(String id);

    Car getCarDetails(String vin);
}
