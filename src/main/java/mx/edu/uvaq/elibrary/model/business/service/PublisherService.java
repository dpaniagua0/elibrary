/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Publisher;

/**
 *
 * @author daniel
 */
public interface PublisherService {

  List<Publisher> getPublishers();

  public boolean registerPublisher(Publisher publishers);

  public Publisher getPublisherById(Long id);

  public boolean removePublisher(Long id);

  public boolean editPublisher(Publisher publisher);
}
