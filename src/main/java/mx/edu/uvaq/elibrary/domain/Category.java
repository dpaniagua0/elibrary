/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.domain;

/**
 * @author arcesino
 */
public class Category {

  private int id;
  private String name;
  private int parentCategoryId;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
