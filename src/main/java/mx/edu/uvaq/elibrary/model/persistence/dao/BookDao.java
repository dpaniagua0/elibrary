/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import mx.edu.uvaq.elibrary.domain.Book;

import java.util.List;

/**
 * @author daniel
 */
public interface BookDao {

  public List<Book> findBooks();

  public Number insertBook(Book book);

  public Book findBookBId(int id);
}
