/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio.impl;

import mx.edu.uvaq.elibrary.modelo.persistencia.dao.EditorialDao;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.SerieDao;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.CategoriaDao;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.AutorDao;
import java.util.List;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.LibrosServicio;
import mx.edu.uvaq.elibrary.modelo.entidades.Libro;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.LibroDao;

/**
 *
 * @author daniel
 */
public class LibrosServicioImpl implements LibrosServicio {

  private LibroDao libroDao;
  private AutorDao autorDao;
  private CategoriaDao categoriaDao;
  private EditorialDao editorialDao;
  private SerieDao serieDao;

  public void setSerieDao(SerieDao serieDao) {
    this.serieDao = serieDao;
  }

  public void setEditorialDao(EditorialDao editorialDao) {
    this.editorialDao = editorialDao;
  }

  public void setCategoriaDao(CategoriaDao categoriaDao) {
    this.categoriaDao = categoriaDao;
  }

  public void setAutorDao(AutorDao autorDao) {
    this.autorDao = autorDao;
  }

  public void setLibroDao(LibroDao libroDao) {
    this.libroDao = libroDao;
  }

  public List<Libro> recuperarLibros() {
    return libroDao.encontrarLibros();
  }

  public Libro recuperarLibroPorId(int id) {
    Libro libro = libroDao.encontrarLibroPorId(id);
    libro = completarInformacionLibro(libro);

    return libro;
  }

  private Libro completarInformacionLibro(Libro libro) {
    libro.setAutores(autorDao.encontrarAutoresDeLibro(libro));
    libro.setCategorias(categoriaDao.encontrarCategoriasDeLibro(libro));
    libro.setEditoriales(editorialDao.encontrarEditorialesDeLibro(libro));
    libro.setSerie(serieDao.encontrarSerieDeLibro(libro));
    
    return libro;
  }

  public Number crearLibro(Libro libro, byte[] archivo, byte[] imagen) {
    // TODO: Validaciones de libro.
    libro.setArchivo(archivo);
    libro.setImagen(imagen);
    Number idGenerado = libroDao.insertarLibro(libro);
    
    // TODO: Insercion de autores, editoriales y categorias.
    
    return idGenerado;
  }
}
