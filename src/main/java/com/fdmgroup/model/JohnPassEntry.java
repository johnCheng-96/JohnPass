package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "john_pass_entry")
public class JohnPassEntry {

  @Id
  @Column(name = "jpEntryId")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int jpEntryId;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "url")
  private String url;

  public JohnPassEntry() {
  }

  public JohnPassEntry(String username, String password, String url) {
    this.username = username;
    this.password = password;
    this.url = url;
  }

  public int getJpEntryId() {
    return jpEntryId;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
