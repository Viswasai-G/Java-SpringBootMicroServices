package com.mySmallCompany.myRentals.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private long phone;

    private Address address;
}
