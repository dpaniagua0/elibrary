/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Categoria;

/**
 *
 * @author daniel
 */
public interface CategoriaServicio {

  List<Categoria> encontrarCategorias();

  public boolean agregarCategoria(Categoria nuevaCategoria);
}
