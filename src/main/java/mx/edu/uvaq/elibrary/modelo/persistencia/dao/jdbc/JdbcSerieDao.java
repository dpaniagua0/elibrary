/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.persistencia.dao.jdbc;

import java.util.HashMap;
import java.util.Map;
import mx.edu.uvaq.elibrary.modelo.entidades.Libro;
import mx.edu.uvaq.elibrary.modelo.entidades.Serie;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.SerieDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author daniel
 */
public class JdbcSerieDao extends QuerysNamedParameterJdbcDaoSupport implements SerieDao {

  private RowMapper<Serie> serieRowMapper;

  public JdbcSerieDao() {
    serieRowMapper = new BeanPropertyRowMapper<Serie>(Serie.class);
  }

  public Serie encontrarSerieDeLibro(Libro libro) {
   String query = querys.get("series.query.encontrarSeriesDeLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int idLibro = libro.getId().intValue();
    queryParams.put("id_libro", idLibro);
    return getNamedParameterJdbcTemplate().queryForObject(query, queryParams, serieRowMapper);
  }
}
