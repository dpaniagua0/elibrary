/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Author;

/**
 *
 * @author daniel
 */
public interface AutoresServicio {

  List<Author> getAutores();

  public Author getAutorPorId(Long idAutor);

  public boolean registrarAutor(Author autor);
  
  public boolean modificarAutor(Author autor);

  public boolean borrarAutor(Long idAutor);
}
