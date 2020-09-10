package com.mySmallCompany.myCarRentals.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class CarNotificationAlertException extends RuntimeException{
    public CarNotificationAlertException(String message) {
        super(message);
    }
}
