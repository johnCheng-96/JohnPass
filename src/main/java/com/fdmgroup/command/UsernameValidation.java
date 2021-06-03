package com.fdmgroup.command;

public interface UsernameValidation extends InputValidation{
  boolean checkIfUsernameExist(String username);

}
