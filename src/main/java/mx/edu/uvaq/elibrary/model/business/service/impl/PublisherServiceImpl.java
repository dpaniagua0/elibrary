/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import mx.edu.uvaq.elibrary.domain.Publisher;
import mx.edu.uvaq.elibrary.model.business.service.PublisherService;
import mx.edu.uvaq.elibrary.model.persistence.dao.PublisherDao;

import java.util.List;

/**
 * @author daniel
 */
public class PublisherServiceImpl implements PublisherService {

  private PublisherDao publisherDao;

  public PublisherDao getPublisherDao() {
    return publisherDao;
  }

  public void setPublisherDao(PublisherDao publisherDao) {
    this.publisherDao = publisherDao;
  }

  public List<Publisher> getPublishers() {
    return publisherDao.findPublishers();
  }

  public boolean registerPublisher(Publisher publishers) {
    if (validateNewPublisher(publishers)) {
      publisherDao.insertPublisher(publishers);
      return true;
    }
    return false;
  }

  public boolean publisherExists(Publisher publisher) {
    Publisher existingPublisher = publisherDao.findPublisherById(null);
    return existingPublisher != null;
  }

  private boolean validateNewPublisher(Publisher publisher) {
    return !publisherExists(publisher);
  }

  public Publisher getPublisherById(Long id) {
    return publisherDao.findPublisherById(id);
  }

  public boolean removePublisher(Long idEditorial) {
    publisherDao.deletePublisher(idEditorial);
    return true;
  }

  public boolean editPublisher(Publisher publisher) {
    publisherDao.updatePublisher(publisher);
    return true;
  }
}
