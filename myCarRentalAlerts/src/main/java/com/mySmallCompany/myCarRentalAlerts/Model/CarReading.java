package com.mySmallCompany.myCarRentalAlerts.Model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CarReading {

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

    TireReading tires;

}
