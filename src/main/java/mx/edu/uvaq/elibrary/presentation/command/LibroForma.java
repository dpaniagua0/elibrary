/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.domain.Libro;
import org.apache.commons.fileupload.FileItemStream;

/**
 *
 * @author arcesino
 */
public class LibroForma extends AbstractForma {
  
  private Libro libro;
  private FileItemStream archivo;
  private FileItemStream imagen;

  public LibroForma() {
    libro = new Libro();
  }

  public Libro getLibro() {
    return libro;
  }

  public void setLibro(Libro libro) {
    this.libro = libro;
  }

  public FileItemStream getArchivo() {
    return archivo;
  }

  public void setArchivo(FileItemStream archivo) {
    this.archivo = archivo;
  }

  public FileItemStream getImagen() {
    return imagen;
  }

  public void setImagen(FileItemStream imagen) {
    this.imagen = imagen;
  }
}
