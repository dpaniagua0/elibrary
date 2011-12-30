/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import mx.edu.uvaq.elibrary.domain.Category;
import mx.edu.uvaq.elibrary.model.business.service.CategoryService;
import mx.edu.uvaq.elibrary.model.persistence.dao.CategoryDao;

import java.util.List;

/**
 * @author daniel
 */
public class CategoryServiceImpl implements CategoryService {

  private CategoryDao categoryDao;

  public CategoryDao getCategoryDao() {
    return categoryDao;
  }

  public void setCategoryDao(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  public List<Category> getCategories() {
    return categoryDao.findCategories();
  }

  public boolean createCategory(Category category) {
    if (validateNewCategory(category)) {
      categoryDao.insertCategory(category);
      return true;
    }
    return false;
  }

  public boolean categoryExists(Category category) {
    Category existingCategory = categoryDao.findCategory(category.getName());

    return existingCategory != null;
  }

  private boolean validateNewCategory(Category category) {
    return !categoryExists(category);
  }
}
