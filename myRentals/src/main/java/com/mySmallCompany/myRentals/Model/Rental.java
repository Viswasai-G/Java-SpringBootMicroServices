package com.mySmallCompany.myRentals.Model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Rental {
    @Id
    private String id;
    private User user;
    private CarReading car;

    public Rental(){
        this.id = UUID.randomUUID().toString();
    }
}
