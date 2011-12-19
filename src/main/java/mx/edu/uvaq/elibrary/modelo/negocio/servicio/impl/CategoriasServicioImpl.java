/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Categoria;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.CategoriaServicio;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.CategoriaDao;

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

  public List<Categoria> encontrarCategorias() {
    return categoriaDao.encontrarCategorias();
  }

  public boolean agregarCategoria(Categoria nuevaCategoria) {
    if (validarNuevaCategoria(nuevaCategoria)) {
      categoriaDao.insertarCategoria(nuevaCategoria);
      return true;
    }
    return false;
  }

  public boolean existeUsuario(Categoria nuevoUsuario) {
    Categoria usuario = categoriaDao.encontrarCategoria(nuevoUsuario.getNombre());

    return usuario != null;
  }

  private boolean validarNuevaCategoria(Categoria nuevaCategoria) {
    return !existeUsuario(nuevaCategoria);
  }
}
