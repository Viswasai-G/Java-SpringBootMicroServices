package com.mySmallCompany.myRentalUsers.Repo;

import com.mySmallCompany.myRentalUsers.Model.User;

import java.util.List;

public interface RentalUserRepo {

    List<User> findBylastName(String lastName);

    List<User> findByEmail(String email);

    List<User> findByCity(String city);

    List<User> findAll();

    User addUser(User user);

    User getAddress(String email);

    void deleteUser(String id) throws Exception;

    User updateUser(User user);

}
