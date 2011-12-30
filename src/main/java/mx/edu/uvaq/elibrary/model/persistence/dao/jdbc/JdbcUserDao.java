/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.persistence.dao.UserDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author daniel
 */
public class JdbcUserDao extends QuerysNamedParameterJdbcDaoSupport implements UserDao {

  private RowMapper<User> userRoleRowMapper;
  private RowMapper<User> userRowMapper;

  public JdbcUserDao() {
    userRoleRowMapper = new RowMapper<User>() {

      public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("correo_electronico"));
        user.setName(rs.getString("nombre"));
        user.setLastName(rs.getString("apellidos"));
        user.setPassword(rs.getString("password"));
        user.setActive(rs.getBoolean("activo"));
        String rol = rs.getString("rol");
        if (rol != null) {
          user.setRoles(new ArrayList<String>());
          user.getRoles().add(rol);
        }
        return user;
      }
    };
    userRowMapper = new BeanPropertyRowMapper<User>(User.class);
  }

  public void insertUser(User user) {
    String query = querys.get("users.query.insertUser");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", user.getEmail());
    queryParams.put("nombre", user.getName());
    queryParams.put("apellidos", user.getLastName());
    queryParams.put("password", user.getPassword());
    queryParams.put("codigo_activacion", user.getActivationCode());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public User findUser(String email) {
    String query = querys.get("users.query.findUser");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", email);
    User user = null;
    try {
      user = getNamedParameterJdbcTemplate().queryForObject(query, queryParams, userRowMapper);
    } catch (EmptyResultDataAccessException dae) {
    }
    return user;
  }

  public List<User> findUsers() {
    String query = querys.get("users.query.findUsers");
    List<User> users = getJdbcTemplate().query(query, userRoleRowMapper);
    return unifyRoles(users);
  }

  public void updateUser(String email) {
    String query = "UPDATE Usuario u set activo = :true , codigo_activacion = :null where u.correo_electronico = :correo_electronico";
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", email);
    queryParams.put("true", true);
    queryParams.put("null", null);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void assignRoleToUser(User user, String role) {
    String query = querys.get("users.query.assignRoleToUser");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", user.getEmail());
    queryParams.put("rol", role);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  private List<User> unifyRoles(List<User> users) {
    Map<String, User> userRoles = new LinkedHashMap<String, User>();
    for (User user : users) {
      User usuarioExistente = userRoles.get(user.getEmail());
      if (usuarioExistente != null) {
        usuarioExistente.getRoles().add(user.getRoles().get(0));
      } else {
        userRoles.put(user.getEmail(), user);
      }
    }
    return new ArrayList<User>(userRoles.values());
  }
}
