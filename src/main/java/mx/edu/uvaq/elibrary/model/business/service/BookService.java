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
public interface BookService {

  List<Book> getBooks();

  Book getBookById(int id);
  
  Number createBook(Book libro, byte[] archivo, byte[] imagen);
}
