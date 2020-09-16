package com.mySmallCompany.myCarRentalAlerts.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class CarRentalAlert {


    @Id
    private String id;
    private Issue issue;
    private String vin;
    private Timestamp timestamp;

    public CarRentalAlert(String vin, Issue issue){
        this.id = UUID.randomUUID().toString();
        this.vin = vin;
        this.issue = issue;
        this.timestamp = Timestamp.from(Instant.now());
    }

}
