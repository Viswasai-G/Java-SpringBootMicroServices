package com.mySmallCompany.myCars.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Year;

@Entity
@Data
public class CarMakeModel {

    @Id
    private String vin;
    private String make;
    private String model;
    private Year year;
    private int redlineRpm;
    private int maxFuelVolume;
    private Timestamp lastServiceDate;
    private boolean inUse;

    public CarMakeModel(){
        this.inUse = false;
    }

}
