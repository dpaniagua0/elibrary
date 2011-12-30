/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.model.business.service.AuthorService;
import mx.edu.uvaq.elibrary.model.persistence.dao.AuthorDao;

import java.util.List;

/**
 * @author daniel
 */
public class AuthorServiceImpl implements AuthorService {

  private AuthorDao authorDao;

  public AuthorDao getAuthorDao() {
    return authorDao;
  }

  public void setAuthorDao(AuthorDao authorDao) {
    this.authorDao = authorDao;
  }

  public List<Author> getAuthors() {
    return authorDao.findAuthors();
  }

  public Author getAuthorById(Long authorId) {
    return authorDao.findAuthorById(authorId);
  }

  public boolean registerAuthor(Author author) {
    if (validateNewUser(author)) {
      authorDao.insertAuthor(author);
      return true;
    }
    return false;
  }

  public boolean authorExists(Author author) {
    Author existingAuthor = authorDao.findAuthorById(Long.valueOf(author.getId()));

    return existingAuthor != null;
  }

  private boolean validateNewUser(Author author) {
    return !authorExists(author);
  }

  public boolean editAuthor(Author author) {
    authorDao.updateAuthor(author);
    return true;
  }

  public boolean removeAuthor(Long id) {
    authorDao.deleteAuthor(id);
    return true;
  }
}
