/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.domain.Category;

import java.util.List;

/**
 * @author arcesino
 */
public interface CategoryDao {

  List<Category> findCategoriesOfBook(Book book);

  List<Category> findCategories();

  public Category findCategory(String name);

  public void insertCategory(Category category);
}
