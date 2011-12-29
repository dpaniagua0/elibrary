/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc.query;

import java.util.Properties;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author arcesino
 */
public class QueryCollection {

  private Properties querys;

  public void setQuerys(Properties querys) {
    this.querys = querys;
  }

  public String get(String queryName) {
    return StringUtils.trim(querys.getProperty(queryName));
  }
}
