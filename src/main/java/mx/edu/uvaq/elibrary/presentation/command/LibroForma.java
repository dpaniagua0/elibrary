/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.domain.Book;
import org.apache.commons.fileupload.FileItemStream;

/**
 *
 * @author arcesino
 */
public class LibroForma extends AbstractForma {
  
  private Book libro;
  private FileItemStream archivo;
  private FileItemStream imagen;

  public LibroForma() {
    libro = new Book();
  }

  public Book getLibro() {
    return libro;
  }

  public void setLibro(Book libro) {
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
