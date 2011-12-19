/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.persistencia.dao.jdbc;

import mx.edu.uvaq.elibrary.modelo.persistencia.dao.jdbc.query.QueryCollection;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 *
 * @author arcesino
 */
public abstract class QuerysNamedParameterJdbcDaoSupport extends NamedParameterJdbcDaoSupport {

  protected QueryCollection querys;

  public QuerysNamedParameterJdbcDaoSupport() {
  }

  public QueryCollection getQuerys() {
    return querys;
  }

  public void setQuerys(QueryCollection querys) {
    this.querys = querys;
  }
}
