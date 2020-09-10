package com.mySmallCompany.myRentalUsers.Service;

import com.mySmallCompany.myRentalUsers.Exception.UserAlreadyPresent;
import com.mySmallCompany.myRentalUsers.Exception.UserNotFoundException;
import com.mySmallCompany.myRentalUsers.Model.Address;
import com.mySmallCompany.myRentalUsers.Model.User;
import com.mySmallCompany.myRentalUsers.Repo.RentalUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class RentalUserServiceImpl implements RentalUserService {

    @Autowired
    private RentalUserRepo rentalUserRepo;

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> userList = rentalUserRepo.findBylastName(lastName);

        return userList;
    }

    @Override
    public User findByEmail(String email) {
        List<User> user = rentalUserRepo.findByEmail(email);
        if(user.size()<1){
            throw new UserNotFoundException("User Not found!");
        }
        return user.get(0);
    }

    @Override
    public List<User> findByCity(String city) {
        return rentalUserRepo.findByCity(city);
    }

    @Override
    public List<User> findAll() {
        return rentalUserRepo.findAll();
    }

    @Override
    public Address getAddress(String email){
        try {
            return rentalUserRepo.getAddress(email).getAddress();
        }
        catch (Exception e){
            throw new UserNotFoundException("User Not found!");
        }
    }

    @Override
    @Transactional
    public User addUser(User user) {
        User exists = rentalUserRepo.addUser(user);
        if(exists==null){
            throw new UserAlreadyPresent("User is already present!");
        }
        return exists;
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        try{
            rentalUserRepo.deleteUser(email);
        }
        catch (Exception e){
            throw new UserNotFoundException("User Not found!");
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if(rentalUserRepo.updateUser(user)==null) {
            throw new UserNotFoundException("User Not found!");
        }
    }
}
