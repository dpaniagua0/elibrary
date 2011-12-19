/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.persistencia.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Editorial;
import mx.edu.uvaq.elibrary.modelo.entidades.Libro;

/**
 *
 * @author arcesino
 */
public interface EditorialDao {

  List<Editorial> encontrarEditorialesDeLibro(Libro libro);

  List<Editorial> encontrarEditoriales();

  public void insertarEditorial(Editorial editorial);

  public Editorial encontrarEditorial(String nombre);
}
