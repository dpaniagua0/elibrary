/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc.mapper;

import mx.edu.uvaq.elibrary.domain.*;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author arcesino
 */
public class BookRowCallbackHandler implements RowCallbackHandler {

  private Map<Integer, Book> book;

  public BookRowCallbackHandler() {
    book = new LinkedHashMap<Integer, Book>();
  }

  public List<Book> getBook() {
    return new ArrayList<Book>(book.values());
  }

  public void processRow(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    Book book = this.book.get(id);
    if (book == null) {
      book = new Book();
      book.setId(Integer.valueOf(id));
      book.setIsbn(rs.getString("isbn"));
      book.setPublishingDate(new Date(rs.getDate("fecha_publicacion").getTime()));
      book.setTitle(rs.getString("titulo"));
      processBookSeries(rs, book);
      this.book.put(id, book);
    }
    processsAuthor(book, rs);
    processCategory(book, rs);
    processPublisher(book, rs);
  }

  private void processBookSeries(ResultSet rs, Book book) throws SQLException {
    BookSeries bookSeries = book.getBookSeries();
    if (bookSeries == null) {
      int bookSeriesId = rs.getInt("id_serie");
      if (bookSeriesId > 0) {
        bookSeries = new BookSeries();
        bookSeries.setId(bookSeriesId);
        bookSeries.setName(rs.getString("serie"));
        book.setBookSeries(bookSeries);
      }
    }
  }

  private void processsAuthor(Book book, ResultSet rs) throws SQLException {
    int authorId = rs.getInt("id_autor");
    if (authorId > 0) {
      boolean authorExists = false;
      for (Author author : book.getAuthors()) {
        if (authorId == author.getId()) {
          authorExists = true;
          break;
        }
      }
      if (!authorExists) {
        Author author = new Author();
        author.setId(authorId);
        author.setName(rs.getString("autor"));
        author.setLastName(rs.getString("apellidos_autor"));
        book.getAuthors().add(author);
      }
    }
  }

  private void processCategory(Book book, ResultSet rs) throws SQLException {
    int categoryId = rs.getInt("id_categoria");
    if (categoryId > 0) {
      boolean categoryDoesNotxists = true;
      for (Category category : book.getCategories()) {
        if (categoryId == category.getId()) {
          categoryDoesNotxists = false;
          break;
        }
      }
      if (categoryDoesNotxists) {
        Category category = new Category();
        category.setId(categoryId);
        category.setName(rs.getString("categoria"));
        book.getCategories().add(category);
      }
    }
  }

  private void processPublisher(Book book, ResultSet rs) throws SQLException {
    int publisherId = rs.getInt("id_editorial");
    if (publisherId > 0) {
      boolean publisherDoesNotExists = true;
      for (Publisher publisher : book.getPublishers()) {
        if (publisherId == publisher.getId()) {
          publisherDoesNotExists = false;
          break;
        }
      }
      if (publisherDoesNotExists) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherId);
        publisher.setName(rs.getString("editorial"));
        book.getPublishers().add(publisher);
      }
    }
  }
}
