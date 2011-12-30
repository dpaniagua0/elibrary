/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.UserService;
import mx.edu.uvaq.elibrary.model.persistence.dao.UserDao;

import java.util.List;

/**
 * @author arcesino
 */
public class UserServiceImpl implements UserService {

  private UserDao userDao;

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<User> getUsers() {
    return userDao.findUsers();
  }

  public void addRole(User user, String role) {
    userDao.assignRoleToUser(user, role);
  }
}
