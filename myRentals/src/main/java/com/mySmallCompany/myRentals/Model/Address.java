package com.mySmallCompany.myRentals.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String id;

    private String street;
    private String city;
    private String state;
    private String country;
}
