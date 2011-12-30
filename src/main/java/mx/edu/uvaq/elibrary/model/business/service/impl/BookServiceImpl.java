/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.business.service.BookService;
import mx.edu.uvaq.elibrary.model.persistence.dao.*;

import java.util.List;

/**
 * @author daniel
 */
public class BookServiceImpl implements BookService {

  private BookDao bookDao;
  private AuthorDao authorDao;
  private CategoryDao categoryDao;
  private PublisherDao publisherDao;
  private BookSeriesDao bookSeriesDao;

  public void setBookSeriesDao(BookSeriesDao bookSeriesDao) {
    this.bookSeriesDao = bookSeriesDao;
  }

  public void setPublisherDao(PublisherDao publisherDao) {
    this.publisherDao = publisherDao;
  }

  public void setCategoryDao(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  public void setAuthorDao(AuthorDao authorDao) {
    this.authorDao = authorDao;
  }

  public void setBookDao(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  public List<Book> getBooks() {
    return bookDao.findBooks();
  }

  public Book getBookById(int id) {
    Book book = bookDao.findBookById(id);
    book = completeBookInformation(book);

    return book;
  }

  private Book completeBookInformation(Book book) {
    book.setAuthors(authorDao.findAuthorsOfBook(book));
    book.setCategories(categoryDao.findCategoriesOfBook(book));
    book.setPublishers(publisherDao.findPublishersOfBook(book));
    book.setBookSeries(bookSeriesDao.findBookSeriesOfBook(book));

    return book;
  }

  public Number createBook(Book book, byte[] file, byte[] image) {
    // TODO: Validaciones de book.
    book.setFile(file);
    book.setImage(image);
    Number id = bookDao.insertBook(book);

    // TODO: Insercion de autores, editoriales y categorias.

    return id;
  }
}
