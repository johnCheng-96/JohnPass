package com.fdmgroup.dal;

import com.fdmgroup.command.UsernameValidation;
import com.fdmgroup.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class UserDAO {

  private EntityManagerFactory emf;
  private UsernameValidation usernameValidation;

  public UserDAO() {

  }

//  public UserDAO(EntityManagerFactory emf) {
//    this();
//    this.emf = emf;
//  }

  public UserDAO(EntityManagerFactory emf, UsernameValidation usernameValidation) {
    this();
    this.emf = emf;
    this.usernameValidation = usernameValidation;
  }

  public void registerNewUser(User user) {
    if (this.usernameValidation.checkIfUsernameExist(user.getUsername())) {
      System.err.println("Username already existed, try another one");
      return;
    }
    EntityManager em = this.emf.createEntityManager();

    EntityTransaction et = em.getTransaction();
    et.begin();
    em.persist(user);
    et.commit();
    em.close();
  }

  public void updatePassword(User user) {
    String username = user.getUsername();
    if (!(this.usernameValidation.checkIfUsernameExist(username))) {
      System.err.println("Username does not existed, try another one");
      return;
    }
    EntityManager em = this.emf.createEntityManager();

    EntityTransaction et = em.getTransaction();
    et.begin();

    TypedQuery<User> query = em
        .createQuery("SELECT user FROM User user WHERE user.username LIKE ?1", User.class);
    query.setParameter(1, username);
    User targetUser = query.getSingleResult();
    targetUser.setPassword(user.getPassword());
    em.flush();

    et.commit();
    em.close();

  }


}
