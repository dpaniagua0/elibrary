/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.domain;

import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author arcesino
 */
public class Author {
  private int id;
  private String name;
  private String lastName;
  private List<Book> books;

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public void setName(String nombre) {
    this.name = nombre;
  }
  
  public String getFullName() {
    return String.format("%s %s", name, StringUtils.defaultString(lastName)).trim();
  }
}
