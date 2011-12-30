/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.persistence.dao.BookDao;
import mx.edu.uvaq.elibrary.model.persistence.dao.jdbc.mapper.BookRowCallbackHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daniel
 */
public class JdbcBookDao extends QuerysNamedParameterJdbcDaoSupport implements BookDao {

  private RowMapper<Book> bookRowMapper;

  public JdbcBookDao() {
    bookRowMapper = new BeanPropertyRowMapper<Book>(Book.class) {

      @Override
      public Book mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Book book = super.mapRow(rs, rowNumber);
        book.setPublishingDate(new Date(book.getPublishingDate().getTime()));
        return book;
      }
    };
  }

  public List<Book> findBooks() {
    String query = querys.get("libros.query.findBooks");
    BookRowCallbackHandler rowHandler = new BookRowCallbackHandler();
    getJdbcTemplate().query(query, rowHandler);
    return rowHandler.getBook();
  }

  public void deleteBook(String isbn) {
    String query = querys.get("libros.query.deleteBook");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("isbn", isbn);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public Number insertBook(Book book) {
    String query = querys.get("libros.query.insertBook");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("isbn", book.getIsbn());
    queryParams.put("titulo", book.getTitle());
    queryParams.put("fecha_publicacion", book.getPublishingDate());
    Integer idSerie = book.getBookSeries().getId();
    queryParams.put("id_serie", idSerie);
    queryParams.put("archivo", book.getFile());
    queryParams.put("imagen", book.getImage());
    KeyHolder key = new GeneratedKeyHolder();
    getNamedParameterJdbcTemplate().update(query, new MapSqlParameterSource(queryParams), key);
    return key.getKey();
  }

  public Book findBookBId(int id) {
    String query = querys.get("libros.query.findBookBId");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", id);
    return getNamedParameterJdbcTemplate().queryForObject(query, queryParams, bookRowMapper);
  }
}
