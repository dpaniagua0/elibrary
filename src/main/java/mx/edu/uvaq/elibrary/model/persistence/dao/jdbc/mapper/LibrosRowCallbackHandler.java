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
import mx.edu.uvaq.elibrary.domain.Autor;
import mx.edu.uvaq.elibrary.domain.Categoria;
import mx.edu.uvaq.elibrary.domain.Editorial;
import mx.edu.uvaq.elibrary.domain.Libro;
import mx.edu.uvaq.elibrary.domain.Serie;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 *
 * @author arcesino
 */
public class LibrosRowCallbackHandler implements RowCallbackHandler {

  private Map<Integer, Libro> libros;

  public LibrosRowCallbackHandler() {
    libros = new LinkedHashMap<Integer, Libro>();
  }

  public List<Libro> getLibros() {
    return new ArrayList<Libro>(libros.values());
  }

  public void processRow(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    Libro libro = libros.get(id);
    if (libro == null) {
      libro = new Libro();
      libro.setId(Integer.valueOf(id));
      libro.setIsbn(rs.getString("isbn"));
      libro.setFechaPublicacion(new Date(rs.getDate("fecha_publicacion").getTime()));
      libro.setTitulo(rs.getString("titulo"));
      procesarSerie(rs, libro);
      libros.put(id, libro);
    }
    procesarAutor(libro, rs);
    procesarCategoria(libro, rs);
    procesarEditorial(libro, rs);
  }

  private void procesarSerie(ResultSet rs, Libro libro) throws SQLException {
    Serie serie = libro.getSerie();
    if (serie == null) {
      int idSerie = rs.getInt("id_serie");
      if (idSerie > 0) {
        serie = new Serie();
        serie.setId(idSerie);
        serie.setNombre(rs.getString("serie"));
        libro.setSerie(serie);
      }
    }
  }

  private void procesarAutor(Libro libro, ResultSet rs) throws SQLException {
    int idAutor = rs.getInt("id_autor");
    if (idAutor > 0) {
      boolean autorNoExiste = true;
      for (Autor a : libro.getAutores()) {
        if (idAutor == a.getId()) {
          autorNoExiste = false;
          break;
        }
      }
      if (autorNoExiste) {
        Autor autor = new Autor();
        autor.setId(idAutor);
        autor.setNombre(rs.getString("autor"));
        autor.setApellidos(rs.getString("apellidos_autor"));
        libro.getAutores().add(autor);
      }
    }
  }
  
  private void procesarCategoria(Libro libro, ResultSet rs) throws SQLException {
    int idCategoria = rs.getInt("id_categoria");
    if (idCategoria > 0) {
      boolean categoriaNoExiste = true;
      for (Categoria c : libro.getCategorias()) {
        if (idCategoria == c.getId()) {
          categoriaNoExiste = false;
          break;
        }
      }
      if (categoriaNoExiste) {
        Categoria categoria = new Categoria();
        categoria.setId(idCategoria);
        categoria.setNombre(rs.getString("categoria"));
        libro.getCategorias().add(categoria);
      }
    }
  }
  
  private void procesarEditorial(Libro libro, ResultSet rs) throws SQLException {
    int idEditorial = rs.getInt("id_editorial");
    if (idEditorial > 0) {
      boolean editorialNoExiste = true;
      for (Editorial a : libro.getEditoriales()) {
        if (idEditorial == a.getId()) {
          editorialNoExiste = false;
          break;
        }
      }
      if (editorialNoExiste) {
        Editorial editorial = new Editorial();
        editorial.setId(idEditorial);
        editorial.setNombre(rs.getString("editorial"));
        libro.getEditoriales().add(editorial);
      }
    }
  }
}
