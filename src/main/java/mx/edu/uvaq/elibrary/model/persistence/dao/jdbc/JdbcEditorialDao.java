/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.domain.Publisher;
import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.persistence.dao.EditorialDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author daniel
 */
public class JdbcEditorialDao extends QuerysNamedParameterJdbcDaoSupport implements EditorialDao {

  private RowMapper<Publisher> editorialRowMapper;

  public JdbcEditorialDao() {
    editorialRowMapper = new BeanPropertyRowMapper<Publisher>(Publisher.class);
  }

  public List<Publisher> encontrarEditorialesDeLibro(Book libro) {
    String query = querys.get("editoriales.query.encontrarEditorialesDeLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int idLibro = libro.getId().intValue();
    queryParams.put("id_libro", idLibro);
    return getNamedParameterJdbcTemplate().query(query.toString(), queryParams, editorialRowMapper);
  }

  public List<Publisher> encontrarEditoriales() {
    String query = querys.get("editoriales.query.encontrarEditoriales");
    List<Publisher> editoriales = getJdbcTemplate().query(query, editorialRowMapper);
    return editoriales;
  }

  public void insertarEditorial(Publisher editorial) {
    String query = querys.get("editoriales.query.insertarEditorial");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", editorial.getName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void actualizarEditorial(Publisher editorial) {
    String query = querys.get("editoriales.query.actualizarEditorial");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", editorial.getId());
    queryParams.put("nombre", editorial.getName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void eliminarEditorial(Long idEditorial) {
    String query = querys.get("editoriales.query.eliminarEditorial");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", idEditorial);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public Publisher encontrarEditorialPorId(Long idEditorial) {
    String query = querys.get("editoriales.query.encontrarEditorial");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", idEditorial);
    Publisher editorial = null;
    try {
      editorial = (Publisher) getNamedParameterJdbcTemplate().queryForObject(query, queryParams, editorialRowMapper);
      editorial.setId(idEditorial.intValue());
    } catch (EmptyResultDataAccessException dae) {
    }
    return editorial;
  }
}
