/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.domain.Book;

/**
 *
 * @author daniel
 */
public class AutoresForma extends AbstractForma {

  private int id;
  private List<Author> autores;
  private String nombre;
  private String apellidos;
  private List<Book> libros;

  public List<Book> getLibros() {
    return libros;
  }

  public void setLibros(List<Book> libros) {
    this.libros = libros;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public List<Author> getAutores() {
    return autores;
  }

  public void setAutores(List<Author> autores) {
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

  public Author getAutor() {
    Author autor = new Author();
    autor.setName(nombre);
    autor.setLastName(apellidos);
    return autor;
  }
}
