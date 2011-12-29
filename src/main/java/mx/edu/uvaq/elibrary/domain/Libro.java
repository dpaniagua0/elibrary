/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.domain;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public class Libro {

  private Integer id;
  private String isbn;
  private String titulo;
  private Date fechaPublicacion;
  private List<Categoria> categorias;
  private List<Editorial> editoriales;
  private Serie serie;
  private List<Autor> autores;
  private byte[] archivo;
  private byte[] imagen;
  private InputStream streamArchivo;
  private InputStream streamImagen;

  public Date getFechaPublicacion() {
    return fechaPublicacion;
  }

  public void setFechaPublicacion(Date fechaPublicacion) {
    this.fechaPublicacion = fechaPublicacion;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Libro() {
    autores = new ArrayList<Autor>();
    categorias = new ArrayList<Categoria>();
    editoriales = new ArrayList<Editorial>();
    serie = new Serie();
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public List<Autor> getAutores() {
    return autores;
  }

  public void setAutores(List<Autor> autores) {
    this.autores = autores;
  }

  public List<Categoria> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<Categoria> categorias) {
    this.categorias = categorias;
  }

  public List<Editorial> getEditoriales() {
    return editoriales;
  }

  public void setEditoriales(List<Editorial> editoriales) {
    this.editoriales = editoriales;
  }

  public Serie getSerie() {
    return serie;
  }

  public void setSerie(Serie serie) {
    this.serie = serie;
  }

  public byte[] getArchivo() {
    return archivo;
  }

  public void setArchivo(byte[] archivo) {
    this.archivo = archivo;
  }

  public byte[] getImagen() {
    return imagen;
  }

  public void setImagen(byte[] imagen) {
    this.imagen = imagen;
  }

  public InputStream getStreamArchivo() {
    return streamArchivo;
  }

  public void setStreamArchivo(InputStream streamArchivo) {
    this.streamArchivo = streamArchivo;
  }

  public InputStream getStreamImagen() {
    return streamImagen;
  }

  public void setStreamImagen(InputStream streamImagen) {
    this.streamImagen = streamImagen;
  }
}
