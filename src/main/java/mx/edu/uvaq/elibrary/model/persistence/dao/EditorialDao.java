/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Editorial;
import mx.edu.uvaq.elibrary.domain.Libro;

/**
 *
 * @author arcesino
 */
public interface EditorialDao {

  List<Editorial> encontrarEditorialesDeLibro(Libro libro);

  List<Editorial> encontrarEditoriales();

  public void insertarEditorial(Editorial editorial);
  
  public void actualizarEditorial(Editorial editorial);
  
  public void eliminarEditorial(Long idEditorial);

  public Editorial encontrarEditorialPorId(Long idEditorial);
}
