/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import mx.edu.uvaq.elibrary.domain.Usuario;

/**
 *
 * @author arcesino
 */
public interface CorreoServicio {

  public abstract void enviarCorreoActivacionCuenta(Usuario usuario, String urlActivacion);
}
