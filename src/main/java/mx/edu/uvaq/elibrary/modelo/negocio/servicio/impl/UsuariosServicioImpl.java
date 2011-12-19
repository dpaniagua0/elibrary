/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Usuario;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.UsuariosServicio;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.UsuarioDao;

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

  public List<Usuario> recuperarUsuarios() {
    return usuarioDao.encontrarUsuarios();
  }

  public void agregarRol(Usuario usuario, String rolUsuario) {
    usuarioDao.asignarRolUsuario(usuario, rolUsuario);
  }
}
