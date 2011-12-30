/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import mx.edu.uvaq.elibrary.domain.Author;

import java.util.List;

/**
 * @author daniel
 */
public interface AuthorService {

  List<Author> getAuthors();

  public Author getAuthorById(Long authorId);

  public boolean registerAuthor(Author author);

  public boolean editAuthor(Author author);

  public boolean removeAuthor(Long authorId);
}
