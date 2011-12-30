/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.domain.BookSeries;
import mx.edu.uvaq.elibrary.model.persistence.dao.BookSeriesDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.HashMap;
import java.util.Map;


/**
 * @author daniel
 */
public class JdbcBookSeriesDao extends QuerysNamedParameterJdbcDaoSupport implements BookSeriesDao {

  private RowMapper<BookSeries> bookSeriesRowMapper;

  public JdbcBookSeriesDao() {
    bookSeriesRowMapper = new BeanPropertyRowMapper<BookSeries>(BookSeries.class);
  }

  public BookSeries findBookSeriesOfBook(Book book) {
    String query = querys.get("series.query.encontrarSeriesDeLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int bookId = book.getId().intValue();
    queryParams.put("id_libro", bookId);
    return getNamedParameterJdbcTemplate().queryForObject(query, queryParams, bookSeriesRowMapper);
  }
}
