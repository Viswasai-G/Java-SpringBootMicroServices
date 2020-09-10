package com.mySmallCompany.myRentalUsers.Controller;

import com.mySmallCompany.myRentalUsers.Model.Address;
import com.mySmallCompany.myRentalUsers.Model.User;
import com.mySmallCompany.myRentalUsers.Service.RentalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class RentalUserController {

    @Autowired
    RentalUserService rentalUserService;

    @RequestMapping(method = RequestMethod.GET, value = "findByEmail={email}/")
    public User findByEmail(@PathVariable("email") String email) {
        return rentalUserService.findByEmail(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "findByLastName={lastName}")
    public List<User> findByLastName(@PathVariable("lastName") String lastName) {
        return rentalUserService.findByLastName(lastName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "findByCity={city}")
    public List<User> findByCity(@PathVariable String city){
        return rentalUserService.findByCity(city);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAll")
    public List<User> findAll() {
        return rentalUserService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addUser")
    public User addUser(@RequestBody User user) {
        return rentalUserService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "getAddress={email}/")
    public Address getAddress(@PathVariable("email") String email) {
        return rentalUserService.getAddress(email);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser={email}/")
    public void deleteUser(@PathVariable("email") String email) {
        rentalUserService.deleteUser(email);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public void updateUser(@RequestBody User user) {
        rentalUserService.updateUser(user);
    }

}