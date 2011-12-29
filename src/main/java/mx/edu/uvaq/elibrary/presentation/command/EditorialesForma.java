/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Editorial;

/**
 *
 * @author daniel
 */
public class EditorialesForma extends AbstractForma {

  private int id;
  private String nombre;
  private List<Editorial> editoriales;

  public List<Editorial> getEditoriales() {
    return editoriales;
  }

  public void setEditoriales(List<Editorial> editoriales) {
    this.editoriales = editoriales;
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

  public Editorial getEditorial() {
    Editorial editorial = new Editorial();
    editorial.setNombre(nombre);
    return editorial;
  }
}
