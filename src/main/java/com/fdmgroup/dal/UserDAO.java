package com.fdmgroup.dal;

import com.fdmgroup.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UserDAO {

  private EntityManagerFactory emf;

  public UserDAO() {
  }

  public UserDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public void registerNewUser(User user) {

    if (checkIfUsernameExist(user.getUsername())) {
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

  private boolean checkIfUsernameExist(String username) {

    EntityManager em = this.emf.createEntityManager();
    TypedQuery<User> query = em
        .createQuery("SELECT user FROM User user WHERE user.username LIKE ?1", User.class);
    query.setParameter(1, username);
    boolean flag = !query.getResultList().isEmpty();
//    System.out.println(flag);
    em.close();
    return flag;



  }
}
