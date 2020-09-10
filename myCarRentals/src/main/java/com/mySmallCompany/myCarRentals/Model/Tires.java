package com.mySmallCompany.myCarRentals.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Tires {


    @Id
    private String id;

    private int frontLeft;
    private int frontRight;
    private int rearLeft;
    private int rearRight;

    public Tires(){
        this.id =
                UUID.randomUUID().toString();
    }

}
