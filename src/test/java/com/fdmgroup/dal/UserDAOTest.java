package com.fdmgroup.dal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdmgroup.command.UsernameValidation;
import com.fdmgroup.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
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
  UsernameValidation mockUsernameValidation;

  @Mock
  TypedQuery<User> mockQuery;


  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void test_if_registerNewUser_persistsToDb() throws NoSuchMethodException {

//    Method checkIfUsernameExist = UserDAO.class.getDeclaredMethod("checkIfUsernameExist");
//    checkIfUsernameExist.setAccessible(true);

    when(mockEmf.createEntityManager()).thenReturn(mockEm);
    when(mockEm.getTransaction()).thenReturn(mockEt);

    UserDAO userDAO = new UserDAO(mockEmf, mockUsernameValidation);

    userDAO.registerNewUser(mockUser);

    InOrder inOrder = inOrder(mockUsernameValidation, mockEmf, mockEm, mockEt);

    verify(mockUsernameValidation).checkIfUsernameExist(mockUser.getUsername());
    verify(mockEmf).createEntityManager();
    verify(mockEm).getTransaction();
    verify(mockEt).begin();
    verify(mockEm).persist(mockUser);
    verify(mockEt).commit();
    verify(mockEm).close();

  }


  @Test
  public void test_if_updatePassword_flushToDb() throws NoSuchMethodException {

//    Method checkIfUsernameExist = UserDAO.class.getDeclaredMethod("checkIfUsernameExist");
//    checkIfUsernameExist.setAccessible(true);
//    when(mockUser.getUsername()).thenReturn(any());
    when(mockUsernameValidation.checkIfUsernameExist(any())).thenReturn(true);
    when(mockEmf.createEntityManager()).thenReturn(mockEm);
    when(mockEm.getTransaction()).thenReturn(mockEt);
    when(mockEm.createQuery(any(String.class), any(Class.class))).thenReturn(mockQuery);
//    when(mockUser.getUsername()).thenReturn("1");
    when(mockQuery.getSingleResult()).thenReturn(mockUser);
    UserDAO userDAO = new UserDAO(mockEmf, mockUsernameValidation);

    userDAO.updatePassword(mockUser);

//    InOrder inOrder = inOrder(mockUsernameValidation, mockEmf, mockEm, mockEt);

    verify(mockUser).getUsername();
    verify(mockUsernameValidation).checkIfUsernameExist(mockUser.getUsername());
    verify(mockEmf).createEntityManager();
    verify(mockEm).getTransaction();
    verify(mockEt).begin();
    verify(mockEm).createQuery(any(String.class), any(Class.class));
    verify(mockQuery).setParameter(1, mockUser.getUsername());
    verify(mockQuery).getSingleResult();
    verify(mockUser).setPassword(mockUser.getPassword());
    verify(mockEm).flush();
    verify(mockEt).commit();
    verify(mockEm).close();

  }

}
