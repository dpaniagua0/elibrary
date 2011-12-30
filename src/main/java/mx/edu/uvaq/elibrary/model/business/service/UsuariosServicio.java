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
public interface UsuariosServicio {

  List<User> recuperarUsuarios();

  public void agregarRol(User usuario, String rolUsuario);
}
