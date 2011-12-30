/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.User;

/**
 *
 * @author arcesino
 */
public interface UserService {

  List<User> getUsers();

  public void addRole(User user, String role);
}
