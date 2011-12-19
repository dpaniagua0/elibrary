/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.forma;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Autor;
import mx.edu.uvaq.elibrary.modelo.entidades.Libro;

/**
 *
 * @author daniel
 */
public class AutoresForma extends AbstractForma {

  private int id;
  private List<Autor> autores;
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

  public List<Autor> getAutores() {
    return autores;
  }

  public void setAutores(List<Autor> autores) {
    this.autores = autores;
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

  public Autor getAutor() {
    Autor autor = new Autor();
    autor.setNombre(nombre);
    autor.setApellidos(apellidos);
    return autor;
  }
}
