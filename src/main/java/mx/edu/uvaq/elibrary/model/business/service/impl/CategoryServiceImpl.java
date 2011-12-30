/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Category;
import mx.edu.uvaq.elibrary.model.business.service.CategoryService;
import mx.edu.uvaq.elibrary.model.persistence.dao.CategoriaDao;

/**
 *
 * @author daniel
 */
public class CategoryServiceImpl implements CategoryService {

  private CategoriaDao categoriaDao;

  public CategoriaDao getCategoriaDao() {
    return categoriaDao;
  }

  public void setCategoriaDao(CategoriaDao categoriaDao) {
    this.categoriaDao = categoriaDao;
  }

  public List<Category> getCategories() {
    return categoriaDao.encontrarCategorias();
  }

  public boolean createCategory(Category category) {
    if (validateNewCategory(category)) {
      categoriaDao.insertarCategoria(category);
      return true;
    }
    return false;
  }

  public boolean categoryExists(Category category) {
    Category existingCategory = categoriaDao.encontrarCategoria(category.getName());

    return existingCategory != null;
  }

  private boolean validateNewCategory(Category category) {
    return !categoryExists(category);
  }
}
