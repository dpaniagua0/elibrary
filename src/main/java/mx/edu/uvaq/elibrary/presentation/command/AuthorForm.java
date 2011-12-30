/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.domain.Book;

import java.util.List;

/**
 * @author daniel
 */
public class AuthorForm extends AbstractForm {

  private int id;
  private List<Author> authors;
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

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
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

  public Author getAuthor() {
    Author author = new Author();
    author.setName(name);
    author.setLastName(lastName);
    return author;
  }
}
