/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.domain;

import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author arcesino
 */
public class Autor {
  private int id;
  private String nombre;
  private String apellidos;
  private List<Libro> libros;

  public List<Libro> getLibros() {
    return libros;
  }

  public void setLibros(List<Libro> libros) {
    this.libros = libros;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getNombreCompleto() {
    return String.format("%s %s", nombre, StringUtils.defaultString(apellidos)).trim();
  }
}
