/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.model.business.service.AutoresServicio;
import mx.edu.uvaq.elibrary.model.persistence.dao.AutorDao;

/**
 *
 * @author daniel
 */
public class AutoresServicioImpl implements AutoresServicio {

  private AutorDao autorDao;

  public AutorDao getAutorDao() {
    return autorDao;
  }

  public void setAutorDao(AutorDao autorDao) {
    this.autorDao = autorDao;
  }

  public List<Author> getAutores() {
    return autorDao.encontrarAutores();
  }

  public Author getAutorPorId(Long idAutor) {
    return autorDao.encontrarAutorPorId(idAutor);
  }

  public boolean registrarAutor(Author nuevoAutor) {
    if (validarNuevoUsuario(nuevoAutor)) {
      autorDao.insertarAutor(nuevoAutor);
      return true;
    }
    return false;
  }

  public boolean existeUsuario(Author autor) {
    Author usuario = autorDao.encontrarAutorPorId(null);

    return usuario != null;
  }

  private boolean validarNuevoUsuario(Author nuevoUsuario) {
    return !existeUsuario(nuevoUsuario);
  }

  public boolean modificarAutor(Author autor) {
    autorDao.actualizarAutor(autor);
    return true;
  }

  public boolean borrarAutor(Long idAutor) {
    autorDao.eliminarAutor(idAutor);
    return true;
  }
}
