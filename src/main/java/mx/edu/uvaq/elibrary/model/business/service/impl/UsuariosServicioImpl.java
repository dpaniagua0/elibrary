/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.UsuariosServicio;
import mx.edu.uvaq.elibrary.model.persistence.dao.UsuarioDao;

/**
 *
 * @author arcesino
 */
public class UsuariosServicioImpl implements UsuariosServicio {

  private UsuarioDao usuarioDao;

  public UsuarioDao getUsuarioDao() {
    return usuarioDao;
  }

  public void setUsuarioDao(UsuarioDao usuarioDao) {
    this.usuarioDao = usuarioDao;
  }

  public List<User> recuperarUsuarios() {
    return usuarioDao.encontrarUsuarios();
  }

  public void agregarRol(User usuario, String rolUsuario) {
    usuarioDao.asignarRolUsuario(usuario, rolUsuario);
  }
}
