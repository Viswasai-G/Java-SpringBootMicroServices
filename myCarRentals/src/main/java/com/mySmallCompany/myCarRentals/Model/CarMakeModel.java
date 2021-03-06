package com.mySmallCompany.myCarRentals.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
public class CarMakeModel {

    private String vin;
    private String make;
    private String model;
    private Year year;
    private int redlineRpm;
    private int maxFuelVolume;
    private Timestamp lastServiceDate;
    private boolean inUse;

}
