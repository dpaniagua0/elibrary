/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Category;

/**
 *
 * @author daniel
 */
public class CategoriasForma extends AbstractForma {

  private List<Category> categorias;
  private int id;
  private String nombre;
  private int idCategoriaPadre;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getIdCategoriaPadre() {
    return idCategoriaPadre;
  }

  public void setIdCategoriaPadre(int idCategoriaPadre) {
    this.idCategoriaPadre = idCategoriaPadre;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Category> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<Category> categorias) {
    this.categorias = categorias;
  }

  public Category getCategoria() {
    Category categoria = new Category();
    categoria.setName(nombre);
    return categoria;
  }
}
