/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.domain.Book;
import org.apache.commons.fileupload.FileItemStream;

/**
 * @author arcesino
 */
public class BookForm extends AbstractForm {

  private Book book;
  private FileItemStream fileStream;
  private FileItemStream imageStream;

  public BookForm() {
    book = new Book();
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public FileItemStream getFileStream() {
    return fileStream;
  }

  public void setFileStream(FileItemStream fileStream) {
    this.fileStream = fileStream;
  }

  public FileItemStream getImageStream() {
    return imageStream;
  }

  public void setImageStream(FileItemStream imageStream) {
    this.imageStream = imageStream;
  }
}
