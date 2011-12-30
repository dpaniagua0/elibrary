/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.persistence.dao.AuthorDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author arcesino
 */
public class JdbcAuthorDao extends QuerysNamedParameterJdbcDaoSupport implements AuthorDao {

  private RowMapper<Author> authorRowMapper;

  public JdbcAuthorDao() {
    authorRowMapper = new BeanPropertyRowMapper<Author>(Author.class);
  }

  public List<Author> findAuthorsOfBook(Book book) {
    String query = querys.get("autores.query.findAuthorsOfBook");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int id = book.getId().intValue();
    queryParams.put("id", id);
    return getNamedParameterJdbcTemplate().query(query, queryParams, authorRowMapper);
  }

  public List<Author> findAuthors() {
    String query = querys.get("autores.query.findAuthors");
    List<Author> authors = getJdbcTemplate().query(query, authorRowMapper);
    return authors;
  }

  public Author findAuthorById(Long id) {
    String query = querys.get("autores.query.encontrarAutor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", id);
    Author author = null;
    try {
      author = (Author) getNamedParameterJdbcTemplate().queryForObject(query, queryParams, authorRowMapper);
      author.setId(id.intValue());
    } catch (EmptyResultDataAccessException dae) {
    }
    return author;
  }

  public void insertAuthor(Author author) {
    String query = querys.get("autores.query.insertAuthor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", author.getName());
    queryParams.put("apellidos", author.getLastName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void updateAuthor(Author author) {
    String query = querys.get("autores.query.updateAuthor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", author.getId());
    queryParams.put("nombre", author.getName());
    queryParams.put("apellidos", author.getLastName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void deleteAuthor(Long id) {
    String query = querys.get("autores.query.deleteAuthor");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", id);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }
}
