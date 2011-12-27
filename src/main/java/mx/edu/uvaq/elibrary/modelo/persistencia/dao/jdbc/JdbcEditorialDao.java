/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.persistencia.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.modelo.entidades.Editorial;
import mx.edu.uvaq.elibrary.modelo.entidades.Libro;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.EditorialDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author daniel
 */
public class JdbcEditorialDao extends QuerysNamedParameterJdbcDaoSupport implements EditorialDao {

  private RowMapper<Editorial> editorialRowMapper;

  public JdbcEditorialDao() {
    editorialRowMapper = new BeanPropertyRowMapper<Editorial>(Editorial.class);
  }

  public List<Editorial> encontrarEditorialesDeLibro(Libro libro) {
    String query = querys.get("editoriales.query.encontrarEditorialesDeLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int idLibro = libro.getId().intValue();
    queryParams.put("id_libro", idLibro);
    return getNamedParameterJdbcTemplate().query(query.toString(), queryParams, editorialRowMapper);
  }

  public List<Editorial> encontrarEditoriales() {
    String query = querys.get("editoriales.query.encontrarEditoriales");
    List<Editorial> editoriales = getJdbcTemplate().query(query, editorialRowMapper);
    return editoriales;
  }

  public void insertarEditorial(Editorial editorial) {
    String query = querys.get("editoriales.query.insertarEditorial");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", editorial.getNombre());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void actualizarEditorial(Editorial editorial) {
    String query = querys.get("editoriales.query.actualizarEditorial");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", editorial.getId());
    queryParams.put("nombre", editorial.getNombre());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void eliminarEditorial(Long idEditorial) {
    String query = querys.get("editoriales.query.eliminarEditorial");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", idEditorial);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public Editorial encontrarEditorialPorId(Long idEditorial) {
    String query = querys.get("editoriales.query.encontrarEditorial");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", idEditorial);
    Editorial editorial = null;
    try {
      editorial = (Editorial) getNamedParameterJdbcTemplate().queryForObject(query, queryParams, editorialRowMapper);
      editorial.setId(idEditorial.intValue());
    } catch (EmptyResultDataAccessException dae) {
    }
    return editorial;
  }
}
