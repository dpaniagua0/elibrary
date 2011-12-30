/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.domain.Publisher;

import java.util.List;

/**
 * @author arcesino
 */
public interface PublisherDao {

  List<Publisher> findPublishersOfBook(Book book);

  List<Publisher> findPublishers();

  public void insertPublishers(Publisher publisher);

  public void updatePublisher(Publisher publisher);

  public void deletePublisher(Long id);

  public Publisher findPublisherById(Long id);
}
