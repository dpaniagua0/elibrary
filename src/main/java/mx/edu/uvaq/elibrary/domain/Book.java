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
 * @author daniel
 */
public class Book {

  private Integer id;
  private String isbn;
  private String title;
  private Date publishingDate;
  private List<Category> categories;
  private List<Publisher> publishers;
  private BookSeries bookSeries;
  private List<Author> authors;
  private byte[] file;
  private byte[] image;
  private InputStream fileStream;
  private InputStream imageStream;

  public Book() {
    authors = new ArrayList<Author>();
    categories = new ArrayList<Category>();
    publishers = new ArrayList<Publisher>();
    bookSeries = new BookSeries();
  }

  public Date getPublishingDate() {
    return publishingDate;
  }

  public void setPublishingDate(Date publishingDate) {
    this.publishingDate = publishingDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public List<Publisher> getPublishers() {
    return publishers;
  }

  public void setPublishers(List<Publisher> publishers) {
    this.publishers = publishers;
  }

  public BookSeries getBookSeries() {
    return bookSeries;
  }

  public void setBookSeries(BookSeries bookSeries) {
    this.bookSeries = bookSeries;
  }

  public byte[] getFile() {
    return file;
  }

  public void setFile(byte[] file) {
    this.file = file;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public InputStream getFileStream() {
    return fileStream;
  }

  public void setFileStream(InputStream fileStream) {
    this.fileStream = fileStream;
  }

  public InputStream getImageStream() {
    return imageStream;
  }

  public void setImageStream(InputStream imageStream) {
    this.imageStream = imageStream;
  }
}
