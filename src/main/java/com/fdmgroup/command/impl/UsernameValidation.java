package com.fdmgroup.command.impl;

import com.fdmgroup.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UsernameValidation {

  public boolean checkIfUsernameExist(String username) {


    EntityManager em = Persistence.createEntityManagerFactory("JohnPassValidate").createEntityManager();
    TypedQuery<User> query = em
        .createQuery("SELECT user FROM User user WHERE user.username LIKE ?1", User.class);
    query.setParameter(1, username);
    boolean flag = !query.getResultList().isEmpty();
//    System.out.println(flag);
    em.close();
    return flag;

  }





}
