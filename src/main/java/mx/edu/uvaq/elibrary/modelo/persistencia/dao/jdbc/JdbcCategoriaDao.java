/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.persistencia.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.modelo.entidades.Categoria;
import mx.edu.uvaq.elibrary.modelo.entidades.Libro;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.CategoriaDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author daniel
 */
public class JdbcCategoriaDao extends QuerysNamedParameterJdbcDaoSupport implements CategoriaDao {

  private RowMapper<Categoria> categoriaRowMapper;

  public JdbcCategoriaDao() {
    categoriaRowMapper = new BeanPropertyRowMapper<Categoria>(Categoria.class);
  }

  public List<Categoria> encontrarCategoriasDeLibro(Libro libro) {
    String query = querys.get("categorias.query.encontrarCategoriasDeLibro");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    int idLibro = libro.getId().intValue();
    queryParams.put("id_libro", idLibro);
    return getNamedParameterJdbcTemplate().query(query, queryParams, categoriaRowMapper);
  }

  public List<Categoria> encontrarCategorias() {
    String query = querys.get("categorias.query.encontrarCategorias");
    List<Categoria> categorias = getJdbcTemplate().query(query, categoriaRowMapper);
    return categorias;
  }

  public Categoria encontrarCategoria(String nombre) {
    String query = querys.get("categorias.query.encontrarCategoria");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", nombre);
    Categoria categoria = null;
    try {
      categoria = (Categoria) getNamedParameterJdbcTemplate().queryForObject(query, queryParams, categoriaRowMapper);
    } catch (EmptyResultDataAccessException dae) {
    }
    return categoria;
  }

  public void insertarCategoria(Categoria nuevaCategoria) {
    String query = querys.get("categorias.query.insertarCategoria");
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("nombre", nuevaCategoria.getNombre());
    getNamedParameterJdbcTemplate().update(query, queryParams);
  }
}
