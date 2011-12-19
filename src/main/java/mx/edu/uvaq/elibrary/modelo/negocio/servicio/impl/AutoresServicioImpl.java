/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Autor;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.AutoresServicio;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.AutorDao;

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

  public List<Autor> encontrarAutores() {
    return autorDao.encontrarAutores();
  }

  public Autor encontrarAutor() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public boolean registrarAutor(Autor nuevoAutor) {
    if (validarNuevoUsuario(nuevoAutor)) {
      autorDao.insertatAutor(nuevoAutor);
      return true;
    }
    return false;
  }

  public boolean existeUsuario(Autor autor) {
    Autor usuario = autorDao.encontrarAutor(autor.getNombre());

    return usuario != null;
  }

  private boolean validarNuevoUsuario(Autor nuevoUsuario) {
    return !existeUsuario(nuevoUsuario);
  }
}
