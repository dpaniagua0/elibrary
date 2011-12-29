/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Autor;

/**
 *
 * @author daniel
 */
public interface AutoresServicio {

  List<Autor> getAutores();

  public Autor getAutorPorId(Long idAutor);

  public boolean registrarAutor(Autor autor);
  
  public boolean modificarAutor(Autor autor);

  public boolean borrarAutor(Long idAutor);
}
