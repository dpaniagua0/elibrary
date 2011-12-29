/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Autor;
import mx.edu.uvaq.elibrary.domain.Libro;

/**
 *
 * @author arcesino
 */
public interface AutorDao {
  List<Autor> encontrarAutoresDeLibro(Libro libro);

  List<Autor> encontrarAutores();

  public Autor encontrarAutorPorId(Long idAutor);

  public void insertarAutor(Autor autor);

  public void actualizarAutor(Autor autor);

  public void eliminarAutor(Long idAutor);
}
