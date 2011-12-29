/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Categoria;
import mx.edu.uvaq.elibrary.domain.Libro;

/**
 *
 * @author arcesino
 */
public interface CategoriaDao {

  List<Categoria> encontrarCategoriasDeLibro(Libro libro);

  List<Categoria> encontrarCategorias();

  public Categoria encontrarCategoria(String nombre);

  public void insertarCategoria(Categoria nuevaCategoria);
}
