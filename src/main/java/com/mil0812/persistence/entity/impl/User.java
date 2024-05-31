package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public record User(
    UUID id,
    String login,
    String password,
    String firstName,
    String lastName,
    byte[] avatar
) implements Entity, Comparable<User> {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(login, user.login)
        && Objects.equals(password, user.password) && Objects.equals(firstName,
        user.firstName) && Objects.equals(lastName, user.lastName)
        && Arrays.equals(avatar, user.avatar);
  }

  //Метод Objects.hash(id, login, password, firstName, lastName);
  // об’єднує значення цих полів і обчислює хеш-код
  @Override
  public int hashCode() {
    int result = Objects.hash(id, login, password, firstName, lastName);
    result = 31 * result + Arrays.hashCode(avatar);
    return result;
  }

  //за чим сортує
  @Override
  public int compareTo(User u) {
    return this.login.compareTo(u.login);
  }

  @Override
  public String toString() {
    return STR."User{id=\{id}, login='\{login}\{'\''}, password='\{password}\{'\''}, firstName='\{firstName}\{'\''}, lastName='\{lastName}\{'\''}, avatar=\{Arrays.toString(
        avatar)}\{'}'}";
  }
}
