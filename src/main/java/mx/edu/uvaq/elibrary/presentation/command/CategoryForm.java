/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.domain.Category;

import java.util.List;

/**
 * @author daniel
 */
public class CategoryForm extends AbstractForm {

  private List<Category> categories;
  private int id;
  private String name;
  private int parentCategoryId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getParentCategoryId() {
    return parentCategoryId;
  }

  public void setParentCategoryId(int parentCategoryId) {
    this.parentCategoryId = parentCategoryId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public Category getCategory() {
    Category category = new Category();
    category.setName(name);
    return category;
  }
}
