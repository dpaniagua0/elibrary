/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.domain.Category;
import mx.edu.uvaq.elibrary.domain.Publisher;
import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.domain.BookSeries;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 *
 * @author arcesino
 */
public class LibrosRowCallbackHandler implements RowCallbackHandler {

  private Map<Integer, Book> libros;

  public LibrosRowCallbackHandler() {
    libros = new LinkedHashMap<Integer, Book>();
  }

  public List<Book> getLibros() {
    return new ArrayList<Book>(libros.values());
  }

  public void processRow(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    Book libro = libros.get(id);
    if (libro == null) {
      libro = new Book();
      libro.setId(Integer.valueOf(id));
      libro.setIsbn(rs.getString("isbn"));
      libro.setPublishingDate(new Date(rs.getDate("fecha_publicacion").getTime()));
      libro.setTitle(rs.getString("titulo"));
      procesarSerie(rs, libro);
      libros.put(id, libro);
    }
    procesarAutor(libro, rs);
    procesarCategoria(libro, rs);
    procesarEditorial(libro, rs);
  }

  private void procesarSerie(ResultSet rs, Book libro) throws SQLException {
    BookSeries serie = libro.getBookSeries();
    if (serie == null) {
      int idSerie = rs.getInt("id_serie");
      if (idSerie > 0) {
        serie = new BookSeries();
        serie.setId(idSerie);
        serie.setName(rs.getString("serie"));
        libro.setBookSeries(serie);
      }
    }
  }

  private void procesarAutor(Book libro, ResultSet rs) throws SQLException {
    int idAutor = rs.getInt("id_autor");
    if (idAutor > 0) {
      boolean autorNoExiste = true;
      for (Author a : libro.getAuthors()) {
        if (idAutor == a.getId()) {
          autorNoExiste = false;
          break;
        }
      }
      if (autorNoExiste) {
        Author autor = new Author();
        autor.setId(idAutor);
        autor.setName(rs.getString("autor"));
        autor.setLastName(rs.getString("apellidos_autor"));
        libro.getAuthors().add(autor);
      }
    }
  }
  
  private void procesarCategoria(Book libro, ResultSet rs) throws SQLException {
    int idCategoria = rs.getInt("id_categoria");
    if (idCategoria > 0) {
      boolean categoriaNoExiste = true;
      for (Category c : libro.getCategories()) {
        if (idCategoria == c.getId()) {
          categoriaNoExiste = false;
          break;
        }
      }
      if (categoriaNoExiste) {
        Category categoria = new Category();
        categoria.setId(idCategoria);
        categoria.setName(rs.getString("categoria"));
        libro.getCategories().add(categoria);
      }
    }
  }
  
  private void procesarEditorial(Book libro, ResultSet rs) throws SQLException {
    int idEditorial = rs.getInt("id_editorial");
    if (idEditorial > 0) {
      boolean editorialNoExiste = true;
      for (Publisher a : libro.getPublishers()) {
        if (idEditorial == a.getId()) {
          editorialNoExiste = false;
          break;
        }
      }
      if (editorialNoExiste) {
        Publisher editorial = new Publisher();
        editorial.setId(idEditorial);
        editorial.setName(rs.getString("editorial"));
        libro.getPublishers().add(editorial);
      }
    }
  }
}
