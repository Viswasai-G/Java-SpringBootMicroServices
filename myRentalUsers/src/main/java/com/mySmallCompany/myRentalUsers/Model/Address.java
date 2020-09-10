package com.mySmallCompany.myRentalUsers.Model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Address {

    @Id
    private String id;

    private String street;
    private String city;
    private String state;
    private String country;

    public Address(){
        this.id = UUID.randomUUID().toString();
    }

}
