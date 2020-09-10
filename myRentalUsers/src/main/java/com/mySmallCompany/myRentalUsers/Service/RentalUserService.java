package com.mySmallCompany.myRentalUsers.Service;

import com.mySmallCompany.myRentalUsers.Model.Address;
import com.mySmallCompany.myRentalUsers.Model.User;

import java.util.List;

public interface RentalUserService {

    List<User> findByLastName(String lastName);

    User findByEmail(String email);

    List<User> findByCity(String city);

    List<User> findAll();

    User addUser(User user);

    Address getAddress(String email);

    void deleteUser(String email);

    void updateUser(User user);

}
