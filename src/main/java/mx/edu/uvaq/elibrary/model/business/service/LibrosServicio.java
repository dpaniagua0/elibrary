/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Book;

/**
 *
 * @author daniel
 */
public interface LibrosServicio {

  List<Book> recuperarLibros();

  Book recuperarLibroPorId(int id);
  
  Number crearLibro(Book libro, byte[] archivo, byte[] imagen);
}
