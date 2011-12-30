/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.domain.Book;

/**
 *
 * @author arcesino
 */
public interface AutorDao {
  List<Author> encontrarAutoresDeLibro(Book libro);

  List<Author> encontrarAutores();

  public Author encontrarAutorPorId(Long idAutor);

  public void insertarAutor(Author autor);

  public void actualizarAutor(Author autor);

  public void eliminarAutor(Long idAutor);
}
