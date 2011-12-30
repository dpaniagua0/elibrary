/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Book;

/**
 *
 * @author daniel
 */
public interface LibroDao {

  public List<Book> encontrarLibros();

  public Number insertarLibro(Book nuevoLibro);

  public Book encontrarLibroPorId(int id);
}
