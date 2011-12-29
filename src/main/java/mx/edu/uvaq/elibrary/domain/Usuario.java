/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.domain;

import java.util.List;

/**
 *
 * @author daniel
 */
public class Usuario {

  private String nombre;
  private String apellidos;
  private String correoElectronico;
  private String password;
  private boolean activo;
  private String codigoActivacion;
  private List<String> roles;

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  public String getCodigoActivacion() {
    return codigoActivacion;
  }

  public void setCodigoActivacion(String codigoActivacion) {
    this.codigoActivacion = codigoActivacion;
  }

  public Usuario() {
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

  public void setPassword(String password) {
    this.password = password;
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
}
