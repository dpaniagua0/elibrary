/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import mx.edu.uvaq.elibrary.domain.User;

/**
 *
 * @author daniel
 */
public interface RegistroServicio {

  public abstract boolean registrarUsuario(User nuevoUsuario, String urlActivacion);

  String generarCodigoActivacion(User usuario);

  boolean activarUsuario(User usuario);
}
