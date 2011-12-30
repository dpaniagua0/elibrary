/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.domain.User;

/**
 *
 * @author daniel
 */
public class RegistroForma extends AbstractForma {

  private String nombre;
  private String apellidos;
  private String correoElectronico;
  private String password;
  private String codigoActivacion;

  public String getCodigoActivacion() {
    return codigoActivacion;
  }

  public void setCodigoActivacion(String codigoActivacion) {
    this.codigoActivacion = codigoActivacion;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String contraseña) {
    this.password = contraseña;
  }

  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public User getUsuario() {
    User usuario = new User();
    usuario.setName(nombre);
    usuario.setLastName(apellidos);
    usuario.setEmail(correoElectronico);
    usuario.setPassword(password);
    usuario.setActivationCode(codigoActivacion);
    return usuario;
  }
}
