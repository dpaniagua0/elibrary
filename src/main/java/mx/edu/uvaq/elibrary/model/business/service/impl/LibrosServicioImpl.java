/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import mx.edu.uvaq.elibrary.model.persistence.dao.EditorialDao;
import mx.edu.uvaq.elibrary.model.persistence.dao.SerieDao;
import mx.edu.uvaq.elibrary.model.persistence.dao.CategoriaDao;
import mx.edu.uvaq.elibrary.model.persistence.dao.AutorDao;
import java.util.List;
import mx.edu.uvaq.elibrary.model.business.service.LibrosServicio;
import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.persistence.dao.LibroDao;

/**
 *
 * @author daniel
 */
public class LibrosServicioImpl implements LibrosServicio {

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

  public List<Book> recuperarLibros() {
    return libroDao.encontrarLibros();
  }

  public Book recuperarLibroPorId(int id) {
    Book libro = libroDao.encontrarLibroPorId(id);
    libro = completarInformacionLibro(libro);

    return libro;
  }

  private Book completarInformacionLibro(Book libro) {
    libro.setAuthors(autorDao.encontrarAutoresDeLibro(libro));
    libro.setCategories(categoriaDao.encontrarCategoriasDeLibro(libro));
    libro.setPublishers(editorialDao.encontrarEditorialesDeLibro(libro));
    libro.setBookSeries(serieDao.encontrarSerieDeLibro(libro));
    
    return libro;
  }

  public Number crearLibro(Book libro, byte[] archivo, byte[] imagen) {
    // TODO: Validaciones de libro.
    libro.setFile(archivo);
    libro.setImage(imagen);
    Number idGenerado = libroDao.insertarLibro(libro);
    
    // TODO: Insercion de autores, editoriales y categorias.
    
    return idGenerado;
  }
}
