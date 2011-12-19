/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.persistencia.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.modelo.entidades.Usuario;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.UsuarioDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author daniel
 */
public class JdbcUsuarioDao extends QuerysNamedParameterJdbcDaoSupport implements UsuarioDao {

  private RowMapper<Usuario> usuarioRolRowMapper;
  private RowMapper<Usuario> usuarioRowMapper;

  public JdbcUsuarioDao() {
    usuarioRolRowMapper = new RowMapper<Usuario>() {

      public Usuario mapRow(ResultSet rs, int i) throws SQLException {
        Usuario usuario = null;
        usuario = new Usuario();
        usuario.setCorreoElectronico(rs.getString("correo_electronico"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellidos(rs.getString("apellidos"));
        usuario.setPassword(rs.getString("password"));
        usuario.setActivo(rs.getBoolean("activo"));
        String rol = rs.getString("rol");
        if (rol != null) {
          usuario.setRoles(new ArrayList<String>());
          usuario.getRoles().add(rol);
        }
        return usuario;
      }
    };
    usuarioRowMapper = new BeanPropertyRowMapper<Usuario>(Usuario.class);
  }

  public void insertarUsuario(Usuario nuevoUsuario) {
    String query = querys.get("usuarios.query.insertarUsuario");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", nuevoUsuario.getCorreoElectronico());
    queryParams.put("nombre", nuevoUsuario.getNombre());
    queryParams.put("apellidos", nuevoUsuario.getApellidos());
    queryParams.put("password", nuevoUsuario.getPassword());
    queryParams.put("codigo_activacion", nuevoUsuario.getCodigoActivacion());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  public Usuario encontrarUsuario(String correoElectronico) {
    String query = querys.get("usuarios.query.encontrarUsuario");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", correoElectronico);
    Usuario usuario = null;
    try {
      usuario = (Usuario) getNamedParameterJdbcTemplate().queryForObject(query, queryParams, usuarioRowMapper);
    } catch (EmptyResultDataAccessException dae) {
    }
    return usuario;
  }

  public List<Usuario> encontrarUsuarios() {
    String query = querys.get("usuarios.query.encontrarUsuarios");
    List<Usuario> usuarios = getJdbcTemplate().query(query, usuarioRolRowMapper);
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

  public void asignarRolUsuario(Usuario usuario, String rolUsuario) {
    String query = querys.get("usuarios.query.asignarRolUsuario");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("correo_electronico", usuario.getCorreoElectronico());
    queryParams.put("rol", rolUsuario);
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }

  private List<Usuario> unificarRoles(List<Usuario> usuarios) {
    Map<String, Usuario> usuariosRoles = new LinkedHashMap<String, Usuario>();
    for (Usuario usuario : usuarios) {
      Usuario usuarioExistente = usuariosRoles.get(usuario.getCorreoElectronico());
      if (usuarioExistente != null) {
        usuarioExistente.getRoles().add(usuario.getRoles().get(0));
      } else {
        usuariosRoles.put(usuario.getCorreoElectronico(), usuario);
      }
    }
    return new ArrayList<Usuario>(usuariosRoles.values());
  }
}
