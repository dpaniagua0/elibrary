/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import mx.edu.uvaq.elibrary.domain.User;

import java.util.List;

/**
 * @author daniel
 */
public interface UserDao {

  public void insertUser(User user);

  public void assignRoleToUser(User user, String role);

  public void updateUser(String email);

  public User findUser(String email);

  public List<User> findUsers();
}
