/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Category;
import mx.edu.uvaq.elibrary.domain.Book;

/**
 *
 * @author arcesino
 */
public interface CategoriaDao {

  List<Category> encontrarCategoriasDeLibro(Book libro);

  List<Category> encontrarCategorias();

  public Category encontrarCategoria(String nombre);

  public void insertarCategoria(Category nuevaCategoria);
}
