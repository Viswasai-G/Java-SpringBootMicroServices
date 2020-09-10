package com.mySmallCompany.myRentalUsers.Repo;

import com.mySmallCompany.myRentalUsers.Model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RentalUserRepoImpl implements RentalUserRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findBylastName(String lastName) {
        TypedQuery<User> query = em.createNamedQuery("findByLastName", User.class);
        query.setParameter("paramLastName", lastName);
        return query.getResultList();
    }

    @Override
    public List<User> findByEmail(String email) {
        TypedQuery<User> query = em.createNamedQuery("findByEmail", User.class);
        query.setParameter("paramEmail", email);
        return query.getResultList();
    }

    @Override
    public List<User> findByCity(String city) {
        TypedQuery<User> query = em.createNamedQuery("findByCity", User.class);
        query.setParameter("paramCity", city);

        return query.getResultList();
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createNamedQuery("findAll", User.class);
        return query.getResultList();
    }

    @Override
    public User getAddress(String email){
        TypedQuery<User> query = em.createNamedQuery("findByEmail", User.class);
        query.setParameter("paramEmail", email);
        return query.getSingleResult();
    }

    @Override
    public User addUser(User user) {
        List<User> exists = findByEmail(user.getEmail());
        if(exists.size()>0){
            return null;
        }
        em.persist(user.getAddress());
        em.persist(user);
        return user;
    }

    @Override
    public void deleteUser(String email) throws Exception {
        List<User> user = findByEmail(email);
        if(user.size()<1){
            throw new Exception("User not found!");
        }
        em.remove(user.get(0));
    }

    @Override
    public User updateUser(User user) {
        List<User> userList = findByEmail(user.getEmail());
        if(userList.size()<1){
            return null;
        }
        return em.merge(user);
    }
}
