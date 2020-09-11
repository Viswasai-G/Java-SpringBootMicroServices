package com.mySmallCompany.myCars.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class NoCarAvailable extends RuntimeException {
    public NoCarAvailable(String message) {
        super(message);
    }
}
