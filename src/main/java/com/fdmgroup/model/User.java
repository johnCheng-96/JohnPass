package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="userId", unique = true)
  private int userId;

  @Column(name="username", unique = true)
  private String username;

  @Column(name="password")
  private String password;

  @OneToMany
  private List<JohnPassEntry> johnPassEntries;

  public User() {
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.johnPassEntries = new ArrayList<>();
  }

  public User(int userId, String username, String password) {
    this.userId = userId;
    this.username = username;
    this.password = password;
  }

  public int getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<JohnPassEntry> getJohnPassEntries() {
    return johnPassEntries;
  }

  public void setJohnPassEntries(List<JohnPassEntry> johnPassEntries) {
    this.johnPassEntries = johnPassEntries;
  }
}
