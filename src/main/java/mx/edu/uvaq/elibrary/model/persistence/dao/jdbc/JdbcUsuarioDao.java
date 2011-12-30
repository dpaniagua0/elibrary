/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.persistence.dao.UsuarioDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author daniel
 */
public class JdbcUsuarioDao extends QuerysNamedParameterJdbcDaoSupport implements UsuarioDao {

  private RowMapper<User> usuarioRolRowMapper;
  private RowMapper<User> usuarioRowMapper;

  public JdbcUsuarioDao() {
    usuarioRolRowMapper = new RowMapper<User>() {

      public User mapRow(ResultSet rs, int i) throws SQLException {
        User usuario = null;
        usuario = new User();
        usuario.setEmail(rs.getString("correo_electronico"));
        usuario.setName(rs.getString("nombre"));
        usuario.setLastName(rs.getString("apellidos"));
        usuario.setPassword(rs.getString("password"));
        usuario.setActive(rs.getBoolean("activo"));
        String rol = rs.getString("rol");
        if (rol != null) {
          usuario.setRoles(new ArrayList<String>());
          usuario.getRoles().add(rol);
        }
        return usuario;
      }
    };
    usuarioRowMapper = new BeanPropertyRowMapper<User>(User.class);
  }

  public void insertarUsuario(User nuevoUsuario) {
    String query = querys.get("usuarios.query.insertarUsuario");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", nuevoUsuario.getEmail());
    queryParams.put("nombre", nuevoUsuario.getName());
    queryParams.put("apellidos", nuevoUsuario.getLastName());
    queryParams.put("password", nuevoUsuario.getPassword());
    queryParams.put("codigo_activacion", nuevoUsuario.getActivationCode());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public User encontrarUsuario(String correoElectronico) {
    String query = querys.get("usuarios.query.encontrarUsuario");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", correoElectronico);
    User usuario = null;
    try {
      usuario = (User) getNamedParameterJdbcTemplate().queryForObject(query, queryParams, usuarioRowMapper);
    } catch (EmptyResultDataAccessException dae) {
    }
    return usuario;
  }

  public List<User> encontrarUsuarios() {
    String query = querys.get("usuarios.query.encontrarUsuarios");
    List<User> usuarios = getJdbcTemplate().query(query, usuarioRolRowMapper);
    return unificarRoles(usuarios);
  }

  public void actualizarUsuario(String correoElectronico) {
    String query = "UPDATE Usuario u set activo = :true , codigo_activacion = :null where u.correo_electronico = :correo_electronico";
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", correoElectronico);
    queryParams.put("true", true);
    queryParams.put("null", null);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public void asignarRolUsuario(User usuario, String rolUsuario) {
    String query = querys.get("usuarios.query.asignarRolUsuario");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", usuario.getEmail());
    queryParams.put("rol", rolUsuario);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  private List<User> unificarRoles(List<User> usuarios) {
    Map<String, User> usuariosRoles = new LinkedHashMap<String, User>();
    for (User usuario : usuarios) {
      User usuarioExistente = usuariosRoles.get(usuario.getEmail());
      if (usuarioExistente != null) {
        usuarioExistente.getRoles().add(usuario.getRoles().get(0));
      } else {
        usuariosRoles.put(usuario.getEmail(), usuario);
      }
    }
    return new ArrayList<User>(usuariosRoles.values());
  }
}
