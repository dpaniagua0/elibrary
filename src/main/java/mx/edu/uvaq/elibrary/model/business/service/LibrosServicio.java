/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Libro;

/**
 *
 * @author daniel
 */
public interface LibrosServicio {

  List<Libro> recuperarLibros();

  Libro recuperarLibroPorId(int id);
  
  Number crearLibro(Libro libro, byte[] archivo, byte[] imagen);
}
