/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.domain.Book;

import java.util.List;

/**
 * @author arcesino
 */
public class BooksForm extends AbstractForm {

  private List<Book> books;
  private List<String> categories;
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }
}
