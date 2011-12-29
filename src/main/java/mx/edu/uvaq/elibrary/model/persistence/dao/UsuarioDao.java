/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Usuario;

/**
 *
 * @author daniel
 */
public interface UsuarioDao {

  public void insertarUsuario(Usuario nuevoUsuario);

  public void asignarRolUsuario(Usuario usuario, String rolUsuario);

  public void actualizarUsuario(String correoElectronico);

  public Usuario encontrarUsuario(String correoElectronico);

  public List<Usuario> encontrarUsuarios();
}
