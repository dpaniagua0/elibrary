/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Usuario;

/**
 *
 * @author arcesino
 */
public interface UsuariosServicio {

  List<Usuario> recuperarUsuarios();

  public void agregarRol(Usuario usuario, String rolUsuario);
}
