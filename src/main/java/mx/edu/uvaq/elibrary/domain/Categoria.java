/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.domain;

/**
 *
 * @author arcesino
 */
public class Categoria {

  private int id;
  private String nombre;
  private int idCategoriaPadre;

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

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
