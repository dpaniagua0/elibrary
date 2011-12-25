/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.persistencia.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.modelo.entidades.Autor;
import mx.edu.uvaq.elibrary.modelo.entidades.Libro;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.AutorDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author arcesino
 */
public class JdbcAutorDao extends QuerysNamedParameterJdbcDaoSupport implements AutorDao {

  private RowMapper<Autor> autorRowMapper;

  public JdbcAutorDao() {
    autorRowMapper = new BeanPropertyRowMapper<Autor>(Autor.class);
  }

  public List<Autor> encontrarAutoresDeLibro(Libro libro) {
    String query = querys.get("autores.query.encontrarAutoresDeLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int id = libro.getId().intValue();
    queryParams.put("id", id);
    return getNamedParameterJdbcTemplate().query(query, queryParams, autorRowMapper);
  }

  public List<Autor> encontrarAutores() {
    String query = querys.get("autores.query.encontrarAutores");
    List<Autor> autores = getJdbcTemplate().query(query, autorRowMapper);
    return autores;
  }

  public Autor encontrarAutorPorId(Long idAutor) {
    String query = querys.get("autores.query.encontrarAutor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", idAutor);
    Autor autor = null;
    try {
      autor = (Autor) getNamedParameterJdbcTemplate().queryForObject(query, queryParams, autorRowMapper);
      autor.setId(idAutor.intValue());
    } catch (EmptyResultDataAccessException dae) {
    }
    return autor;
  }

  public void insertarAutor(Autor autor) {
    String query = querys.get("autores.query.insertarAutor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", autor.getNombre());
    queryParams.put("apellidos", autor.getApellidos());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void actualizarAutor(Autor autor) {
    String query = querys.get("autores.query.actualizarAutor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", autor.getId());
    queryParams.put("nombre", autor.getNombre());
    queryParams.put("apellidos", autor.getApellidos());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }
}
