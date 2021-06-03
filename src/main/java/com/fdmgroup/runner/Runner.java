package com.fdmgroup.runner;

import com.fdmgroup.command.impl.UsernameValidationImpl;
import com.fdmgroup.dal.UserDAO;
import com.fdmgroup.model.User;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Runner {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(
        "JohnPassUpdate");

    UserDAO userDAO = new UserDAO(emf, new UsernameValidationImpl());

    userDAO.updatePassword(new User(3, "kimsiang", "12345666"));

  }

}
