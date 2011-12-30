/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.model.business.service.AuthorService;
import mx.edu.uvaq.elibrary.model.persistence.dao.AutorDao;

/**
 *
 * @author daniel
 */
public class AuthorServiceImpl implements AuthorService {

  private AutorDao autorDao;

  public AutorDao getAutorDao() {
    return autorDao;
  }

  public void setAutorDao(AutorDao autorDao) {
    this.autorDao = autorDao;
  }

  public List<Author> getAuthors() {
    return autorDao.encontrarAutores();
  }

  public Author getAuthorById(Long authorId) {
    return autorDao.encontrarAutorPorId(authorId);
  }

  public boolean registerAuthor(Author author) {
    if (validateNewUser(author)) {
      autorDao.insertarAutor(author);
      return true;
    }
    return false;
  }

  public boolean authorExists(Author author) {
    Author existingAuthor = autorDao.encontrarAutorPorId(Long.valueOf(author.getId()));

    return existingAuthor != null;
  }

  private boolean validateNewUser(Author author) {
    return !authorExists(author);
  }

  public boolean editAuthor(Author author) {
    autorDao.actualizarAutor(author);
    return true;
  }

  public boolean removeAuthor(Long id) {
    autorDao.eliminarAutor(id);
    return true;
  }
}
