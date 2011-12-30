/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.persistence.dao.AutorDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author arcesino
 */
public class JdbcAutorDao extends QuerysNamedParameterJdbcDaoSupport implements AutorDao {

  private RowMapper<Author> autorRowMapper;

  public JdbcAutorDao() {
    autorRowMapper = new BeanPropertyRowMapper<Author>(Author.class);
  }

  public List<Author> encontrarAutoresDeLibro(Book libro) {
    String query = querys.get("autores.query.encontrarAutoresDeLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int id = libro.getId().intValue();
    queryParams.put("id", id);
    return getNamedParameterJdbcTemplate().query(query, queryParams, autorRowMapper);
  }

  public List<Author> encontrarAutores() {
    String query = querys.get("autores.query.encontrarAutores");
    List<Author> autores = getJdbcTemplate().query(query, autorRowMapper);
    return autores;
  }

  public Author encontrarAutorPorId(Long idAutor) {
    String query = querys.get("autores.query.encontrarAutor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", idAutor);
    Author autor = null;
    try {
      autor = (Author) getNamedParameterJdbcTemplate().queryForObject(query, queryParams, autorRowMapper);
      autor.setId(idAutor.intValue());
    } catch (EmptyResultDataAccessException dae) {
    }
    return autor;
  }

  public void insertarAutor(Author autor) {
    String query = querys.get("autores.query.insertarAutor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", autor.getName());
    queryParams.put("apellidos", autor.getLastName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void actualizarAutor(Author autor) {
    String query = querys.get("autores.query.actualizarAutor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", autor.getId());
    queryParams.put("nombre", autor.getName());
    queryParams.put("apellidos", autor.getLastName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void eliminarAutor(Long idAutor) {
    String query = querys.get("autores.query.eliminarAutor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", idAutor);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }
}
