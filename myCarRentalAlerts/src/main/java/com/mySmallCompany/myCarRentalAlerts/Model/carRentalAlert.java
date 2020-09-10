package com.mySmallCompany.myCarRentalAlerts.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class carRentalAlert {


    @Id
    private String id;
    private Issue issue;
    private String vin;

    public carRentalAlert(String vin, Issue issue){
        this.id = UUID.randomUUID().toString();
        this.vin = vin;
        this.issue = issue;
    }

}
