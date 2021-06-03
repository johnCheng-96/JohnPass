package com.fdmgroup.dal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdmgroup.model.User;
import java.lang.reflect.Method;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

  @Mock
  EntityManagerFactory mockEmf;

  @Mock
  EntityManager mockEm;

  @Mock
  EntityTransaction mockEt;

  @Mock
  UserDAO mockUserDAO;

  @Mock
  User mockUser;

  @Mock
  String username;

  @Test
  public void test_if_registerNewUser_persistsToDb() throws NoSuchMethodException {

    Method checkIfUsernameExist = UserDAO.class.getDeclaredMethod("checkIfUsernameExist");
    checkIfUsernameExist.setAccessible(true);

    when(mockEmf.createEntityManager()).thenReturn(mockEm);
    when(mockEm.getTransaction()).thenReturn(mockEt);

    UserDAO userDAO = new UserDAO(mockEmf);

    userDAO.registerNewUser(mockUser);

    verify(mockEmf).createEntityManager();
    verify(mockEm).getTransaction();
    verify(mockEt).begin();
    verify(mockEm).persist(mockUser);
    verify(mockEt).commit();
    verify(mockEm).close();

  }

}
