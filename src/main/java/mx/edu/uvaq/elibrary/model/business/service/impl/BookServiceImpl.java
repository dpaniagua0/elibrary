/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import mx.edu.uvaq.elibrary.model.business.service.BookService;
import mx.edu.uvaq.elibrary.model.persistence.dao.EditorialDao;
import mx.edu.uvaq.elibrary.model.persistence.dao.SerieDao;
import mx.edu.uvaq.elibrary.model.persistence.dao.CategoriaDao;
import mx.edu.uvaq.elibrary.model.persistence.dao.AutorDao;
import java.util.List;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.persistence.dao.LibroDao;

/**
 *
 * @author daniel
 */
public class BookServiceImpl implements BookService {

  private LibroDao libroDao;
  private AutorDao autorDao;
  private CategoriaDao categoriaDao;
  private EditorialDao editorialDao;
  private SerieDao serieDao;

  public void setSerieDao(SerieDao serieDao) {
    this.serieDao = serieDao;
  }

  public void setEditorialDao(EditorialDao editorialDao) {
    this.editorialDao = editorialDao;
  }

  public void setCategoriaDao(CategoriaDao categoriaDao) {
    this.categoriaDao = categoriaDao;
  }

  public void setAutorDao(AutorDao autorDao) {
    this.autorDao = autorDao;
  }

  public void setLibroDao(LibroDao libroDao) {
    this.libroDao = libroDao;
  }

  public List<Book> getBooks() {
    return libroDao.encontrarLibros();
  }

  public Book getBookById(int id) {
    Book book = libroDao.encontrarLibroPorId(id);
    book = completeBookInformation(book);

    return book;
  }

  private Book completeBookInformation(Book book) {
    book.setAuthors(autorDao.encontrarAutoresDeLibro(book));
    book.setCategories(categoriaDao.encontrarCategoriasDeLibro(book));
    book.setPublishers(editorialDao.encontrarEditorialesDeLibro(book));
    book.setBookSeries(serieDao.encontrarSerieDeLibro(book));
    
    return book;
  }

  public Number createBook(Book book, byte[] file, byte[] image) {
    // TODO: Validaciones de book.
    book.setFile(file);
    book.setImage(image);
    Number id = libroDao.insertarLibro(book);
    
    // TODO: Insercion de autores, editoriales y categorias.
    
    return id;
  }
}
