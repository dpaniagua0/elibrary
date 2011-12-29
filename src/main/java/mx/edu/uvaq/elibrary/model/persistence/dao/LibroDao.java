/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Libro;

/**
 *
 * @author daniel
 */
public interface LibroDao {

  public List<Libro> encontrarLibros();

  public Number insertarLibro(Libro nuevoLibro);

  public Libro encontrarLibroPorId(int id);
}
