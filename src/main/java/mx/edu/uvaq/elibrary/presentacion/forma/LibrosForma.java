/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.forma;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Libro;

/**
 *
 * @author arcesino
 */
public class LibrosForma extends AbstractForma {

  private List<Libro> libros;
  private List<String> categorias;
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<String> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<String> categorias) {
    this.categorias = categorias;
  }

  public List<Libro> getLibros() {
    return libros;
  }

  public void setLibros(List<Libro> libros) {
    this.libros = libros;
  }
}
