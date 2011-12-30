/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Category;
import mx.edu.uvaq.elibrary.model.business.service.CategoriaServicio;
import mx.edu.uvaq.elibrary.model.persistence.dao.CategoriaDao;

/**
 *
 * @author daniel
 */
public class CategoriasServicioImpl implements CategoriaServicio {

  private CategoriaDao categoriaDao;

  public CategoriaDao getCategoriaDao() {
    return categoriaDao;
  }

  public void setCategoriaDao(CategoriaDao categoriaDao) {
    this.categoriaDao = categoriaDao;
  }

  public List<Category> encontrarCategorias() {
    return categoriaDao.encontrarCategorias();
  }

  public boolean agregarCategoria(Category nuevaCategoria) {
    if (validarNuevaCategoria(nuevaCategoria)) {
      categoriaDao.insertarCategoria(nuevaCategoria);
      return true;
    }
    return false;
  }

  public boolean existeUsuario(Category nuevoUsuario) {
    Category usuario = categoriaDao.encontrarCategoria(nuevoUsuario.getName());

    return usuario != null;
  }

  private boolean validarNuevaCategoria(Category nuevaCategoria) {
    return !existeUsuario(nuevaCategoria);
  }
}
