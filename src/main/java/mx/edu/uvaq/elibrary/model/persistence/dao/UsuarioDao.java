/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.User;

/**
 *
 * @author daniel
 */
public interface UsuarioDao {

  public void insertarUsuario(User nuevoUsuario);

  public void asignarRolUsuario(User usuario, String rolUsuario);

  public void actualizarUsuario(String correoElectronico);

  public User encontrarUsuario(String correoElectronico);

  public List<User> encontrarUsuarios();
}
