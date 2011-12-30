/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.domain.Category;
import mx.edu.uvaq.elibrary.model.persistence.dao.CategoryDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daniel
 */
public class JdbcCategoryDao extends QuerysNamedParameterJdbcDaoSupport implements CategoryDao {

  private RowMapper<Category> categoryRowMapper;

  public JdbcCategoryDao() {
    categoryRowMapper = new BeanPropertyRowMapper<Category>(Category.class);
  }

  public List<Category> findCategoriesOfBook(Book book) {
    String query = querys.get("categorias.query.findCategoriesOfBook");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int bookId = book.getId().intValue();
    queryParams.put("id_libro", bookId);
    return getNamedParameterJdbcTemplate().query(query, queryParams, categoryRowMapper);
  }

  public List<Category> findCategories() {
    String query = querys.get("categorias.query.getCategories");
    List<Category> categorias = getJdbcTemplate().query(query, categoryRowMapper);
    return categorias;
  }

  public Category findCategory(String name) {
    String query = querys.get("categorias.query.findCategory");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("name", name);
    Category category = null;
    try {
      category = getNamedParameterJdbcTemplate().queryForObject(query, queryParams, categoryRowMapper);
    } catch (EmptyResultDataAccessException dae) {
    }
    return category;
  }

  public void insertCategory(Category nuevaCategoria) {
    String query = querys.get("categorias.query.insertCategory");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("name", nuevaCategoria.getName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }
}
