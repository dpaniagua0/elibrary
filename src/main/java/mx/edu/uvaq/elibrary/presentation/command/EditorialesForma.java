/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Publisher;

/**
 *
 * @author daniel
 */
public class EditorialesForma extends AbstractForma {

  private int id;
  private String nombre;
  private List<Publisher> editoriales;

  public List<Publisher> getEditoriales() {
    return editoriales;
  }

  public void setEditoriales(List<Publisher> editoriales) {
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

  public Publisher getEditorial() {
    Publisher editorial = new Publisher();
    editorial.setName(nombre);
    return editorial;
  }
}
