package com.mySmallCompany.myCarRentalAlerts.Model;


import lombok.Data;

@Data
public class TireReading {


    private String id;

    private int frontLeft;
    private int frontRight;
    private int rearLeft;
    private int rearRight;

}
