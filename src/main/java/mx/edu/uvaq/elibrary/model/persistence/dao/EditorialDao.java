/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Publisher;
import mx.edu.uvaq.elibrary.domain.Book;

/**
 *
 * @author arcesino
 */
public interface EditorialDao {

  List<Publisher> encontrarEditorialesDeLibro(Book libro);

  List<Publisher> encontrarEditoriales();

  public void insertarEditorial(Publisher editorial);
  
  public void actualizarEditorial(Publisher editorial);
  
  public void eliminarEditorial(Long idEditorial);

  public Publisher encontrarEditorialPorId(Long idEditorial);
}
