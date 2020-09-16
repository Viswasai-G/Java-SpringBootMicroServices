package com.mySmallCompany.myRentals.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TireReading {
    private String id;
    private int frontLeft;
    private int frontRight;
    private int rearLeft;
    private int rearRight;
}
