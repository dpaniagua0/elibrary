/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.domain.Book;

import java.util.List;

/**
 * @author arcesino
 */
public interface AuthorDao {
  List<Author> findAuthorsOfBook(Book book);

  List<Author> findAuthors();

  public Author findAuthorById(Long id);

  public void insertAuthor(Author author);

  public void updateAuthor(Author author);

  public void deleteAuthor(Long id);
}
