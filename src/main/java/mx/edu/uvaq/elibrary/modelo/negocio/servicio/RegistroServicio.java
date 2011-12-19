/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio;

import mx.edu.uvaq.elibrary.modelo.entidades.Usuario;

/**
 *
 * @author daniel
 */
public interface RegistroServicio {

  public abstract boolean registrarUsuario(Usuario nuevoUsuario, String urlActivacion);

  String generarCodigoActivacion(Usuario usuario);

  boolean activarUsuario(Usuario usuario);
}
