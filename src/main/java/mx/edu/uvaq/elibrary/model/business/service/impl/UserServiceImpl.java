/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.UserService;
import mx.edu.uvaq.elibrary.model.persistence.dao.UsuarioDao;

/**
 *
 * @author arcesino
 */
public class UserServiceImpl implements UserService {

  private UsuarioDao usuarioDao;

  public UsuarioDao getUsuarioDao() {
    return usuarioDao;
  }

  public void setUsuarioDao(UsuarioDao usuarioDao) {
    this.usuarioDao = usuarioDao;
  }

  public List<User> getUsers() {
    return usuarioDao.encontrarUsuarios();
  }

  public void addRole(User user, String role) {
    usuarioDao.asignarRolUsuario(user, role);
  }
}
