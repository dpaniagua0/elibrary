/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.domain.Publisher;
import mx.edu.uvaq.elibrary.model.persistence.dao.PublisherDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daniel
 */
public class JdbcPublisherDao extends QuerysNamedParameterJdbcDaoSupport implements PublisherDao {

  private RowMapper<Publisher> publisherRowMapper;

  public JdbcPublisherDao() {
    publisherRowMapper = new BeanPropertyRowMapper<Publisher>(Publisher.class);
  }

  public List<Publisher> findPublishersOfBook(Book book) {
    String query = querys.get("publishers.query.findPublishersOfBook");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int bookId = book.getId().intValue();
    queryParams.put("id_libro", bookId);
    return getNamedParameterJdbcTemplate().query(query.toString(), queryParams, publisherRowMapper);
  }

  public List<Publisher> findPublishers() {
    String query = querys.get("publishers.query.findPublishers");
    List<Publisher> publishers = getJdbcTemplate().query(query, publisherRowMapper);
    return publishers;
  }

  public void insertPublisher(Publisher publisher) {
    String query = querys.get("publishers.query.insertPublisher");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", publisher.getName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void updatePublisher(Publisher publisher) {
    String query = querys.get("publishers.query.updatePublisher");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", publisher.getId());
    queryParams.put("nombre", publisher.getName());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void deletePublisher(Long id) {
    String query = querys.get("publishers.query.deletePublisher");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", id);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public Publisher findPublisherById(Long id) {
    String query = querys.get("publishers.query.findPublisherById");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("id", id);
    Publisher publisher = null;
    try {
      publisher = getNamedParameterJdbcTemplate().queryForObject(query, queryParams, publisherRowMapper);
      publisher.setId(id.intValue());
    } catch (EmptyResultDataAccessException dae) {
    }
    return publisher;
  }
}
