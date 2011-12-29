/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.domain.Libro;
import mx.edu.uvaq.elibrary.model.persistence.dao.LibroDao;
import mx.edu.uvaq.elibrary.model.persistence.dao.jdbc.mapper.LibrosRowCallbackHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author daniel
 */
public class JdbcLibroDao extends QuerysNamedParameterJdbcDaoSupport implements LibroDao {

  private RowMapper<Libro> libroRowMapper;

  public JdbcLibroDao() {
    libroRowMapper = new BeanPropertyRowMapper<Libro>(Libro.class) {

      @Override
      public Libro mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Libro libro = super.mapRow(rs, rowNumber);
        libro.setFechaPublicacion(new Date(libro.getFechaPublicacion().getTime()));
        return libro;
      }
    };
  }

  public List<Libro> encontrarLibros() {
    String query = querys.get("libros.query.encontrarLibros");
    LibrosRowCallbackHandler rowHandler = new LibrosRowCallbackHandler();
    getJdbcTemplate().query(query, rowHandler);
    return rowHandler.getLibros();
  }

  public void deleteBook(String isbn) {
    String query = querys.get("libros.query.deleteBook");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("isbn", isbn);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public Number insertarLibro(Libro nuevoLibro) {
    String query = querys.get("libros.query.insertarLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("isbn", nuevoLibro.getIsbn());
    queryParams.put("titulo", nuevoLibro.getTitulo());
    queryParams.put("fecha_publicacion", nuevoLibro.getFechaPublicacion());
    Integer idSerie = nuevoLibro.getSerie().getId();
    queryParams.put("id_serie", idSerie);
    queryParams.put("archivo", nuevoLibro.getArchivo());
    queryParams.put("imagen", nuevoLibro.getImagen());
    KeyHolder idGenerado = new GeneratedKeyHolder();
    getNamedParameterJdbcTemplate().update(query, new MapSqlParameterSource(queryParams), idGenerado);
    return idGenerado.getKey();
  }

  public Libro encontrarLibroPorId(int id) {
    String query = querys.get("libros.query.encontrarLibroPorId");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", id);
    return getNamedParameterJdbcTemplate().queryForObject(query, queryParams, libroRowMapper);
  }
}
