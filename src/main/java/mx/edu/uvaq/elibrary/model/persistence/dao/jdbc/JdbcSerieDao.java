/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import java.util.HashMap;
import java.util.Map;
import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.domain.BookSeries;
import mx.edu.uvaq.elibrary.model.persistence.dao.SerieDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author daniel
 */
public class JdbcSerieDao extends QuerysNamedParameterJdbcDaoSupport implements SerieDao {

  private RowMapper<BookSeries> serieRowMapper;

  public JdbcSerieDao() {
    serieRowMapper = new BeanPropertyRowMapper<BookSeries>(BookSeries.class);
  }

  public BookSeries encontrarSerieDeLibro(Book libro) {
   String query = querys.get("series.query.encontrarSeriesDeLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int idLibro = libro.getId().intValue();
    queryParams.put("id_libro", idLibro);
    return getNamedParameterJdbcTemplate().queryForObject(query, queryParams, serieRowMapper);
  }
}
