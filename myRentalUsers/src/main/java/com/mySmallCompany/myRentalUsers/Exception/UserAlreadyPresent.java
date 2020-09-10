package com.mySmallCompany.myRentalUsers.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyPresent extends RuntimeException{

    public UserAlreadyPresent(String msg){
        super(msg);
    }

}
