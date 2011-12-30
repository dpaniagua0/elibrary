/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Category;

/**
 *
 * @author daniel
 */
public interface CategoriaServicio {

  List<Category> encontrarCategorias();

  public boolean agregarCategoria(Category nuevaCategoria);
}
