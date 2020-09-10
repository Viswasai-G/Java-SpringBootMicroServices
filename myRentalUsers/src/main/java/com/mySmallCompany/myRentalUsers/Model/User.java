package com.mySmallCompany.myRentalUsers.Model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "findByLastName", query = "select user from User user where user.lastName=:paramLastName"),
        @NamedQuery(name = "findByEmail", query = "select user from User user where user.email=:paramEmail"),
        @NamedQuery(name = "findByCity", query = "select user from User user where user.address.city=:paramCity"),
        @NamedQuery(name = "findAll", query = "select user from User user")
})
public class User {

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private long phone;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Address address;
}
