/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.domain.Category;
import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.persistence.dao.CategoriaDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author daniel
 */
public class JdbcCategoriaDao extends QuerysNamedParameterJdbcDaoSupport implements CategoriaDao {

  private RowMapper<Category> categoriaRowMapper;

  public JdbcCategoriaDao() {
    categoriaRowMapper = new BeanPropertyRowMapper<Category>(Category.class);
  }

  public List<Category> encontrarCategoriasDeLibro(Book libro) {
    String query = querys.get("categorias.query.encontrarCategoriasDeLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int idLibro = libro.getId().intValue();
    queryParams.put("id_libro", idLibro);
    return getNamedParameterJdbcTemplate().query(query, queryParams, categoriaRowMapper);
  }

  public List<Category> encontrarCategorias() {
    String query = querys.get("categorias.query.getCategories");
    List<Category> categorias = getJdbcTemplate().query(query, categoriaRowMapper);
    return categorias;
  }

  public Category encontrarCategoria(String nombre) {
    String query = querys.get("categorias.query.encontrarCategoria");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", nombre);
    Category categoria = null;
    try {
      categoria = (Category) getNamedParameterJdbcTemplate().queryForObject(query, queryParams, categoriaRowMapper);
    } catch (EmptyResultDataAccessException dae) {
    }
    return categoria;
  }

  public void insertarCategoria(Category nuevaCategoria) {
    String query = querys.get("categorias.query.insertarCategoria");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", nuevaCategoria.getName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }
}
