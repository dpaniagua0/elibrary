/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.persistencia.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Autor;
import mx.edu.uvaq.elibrary.modelo.entidades.Libro;

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
}
