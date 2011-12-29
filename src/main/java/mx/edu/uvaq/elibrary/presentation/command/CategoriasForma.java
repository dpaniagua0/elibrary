/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Categoria;

/**
 *
 * @author daniel
 */
public class CategoriasForma extends AbstractForma {

  private List<Categoria> categorias;
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

  public List<Categoria> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<Categoria> categorias) {
    this.categorias = categorias;
  }

  public Categoria getCategoria() {
    Categoria categoria = new Categoria();
    categoria.setNombre(nombre);
    return categoria;
  }
}
