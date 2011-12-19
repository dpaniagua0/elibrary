/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Autor;

/**
 *
 * @author daniel
 */
public interface AutoresServicio {

  List<Autor> encontrarAutores();

  public Autor encontrarAutor();

  public boolean registrarAutor(Autor autor);
}
