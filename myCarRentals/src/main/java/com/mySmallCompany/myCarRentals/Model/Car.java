package com.mySmallCompany.myCarRentals.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    private String vin;
    private double latitude;
    private double longitude;
    private Timestamp timestamp;
    private float fuelVolume;
    private int speed;
    private int engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private int engineRpm;

    @OneToOne(cascade = CascadeType.ALL)
    Tires tires;
}
