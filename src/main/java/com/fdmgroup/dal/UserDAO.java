package com.fdmgroup.dal;

import com.fdmgroup.command.InputValidation;
import com.fdmgroup.command.impl.UsernameValidation;
import com.fdmgroup.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UserDAO {

  private EntityManagerFactory emf;
  private InputValidation inputValid;

  public UserDAO() {
  }

  public UserDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }


  public void registerNewUser(User user) {

    if (new UsernameValidation().checkIfUsernameExist(user.getUsername())) {
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

    if (!(new UsernameValidation().checkIfUsernameExist(user.getUsername()))) {
      System.err.println("Username does not existed, try another one");
      return;
    }
    EntityManager em = this.emf.createEntityManager();

    EntityTransaction et = em.getTransaction();
    et.begin();

    TypedQuery<User> query = em
        .createQuery("SELECT user FROM User user WHERE user.username LIKE ?1", User.class);
    query.setParameter(1, user.getUsername());
    User targetUser = query.getSingleResult();
    targetUser.setPassword(user.getPassword());
    em.flush();

    et.commit();
    em.close();

  }
}
