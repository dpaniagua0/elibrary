/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Publisher;
import mx.edu.uvaq.elibrary.model.business.service.PublisherService;
import mx.edu.uvaq.elibrary.model.persistence.dao.EditorialDao;

/**
 *
 * @author daniel
 */
public class PublisherServiceImpl implements PublisherService {

  private EditorialDao editorialDao;

  public EditorialDao getEditorialDao() {
    return editorialDao;
  }

  public void setEditorialDao(EditorialDao editorialDao) {
    this.editorialDao = editorialDao;
  }

  public List<Publisher> getPublishers() {
    return editorialDao.encontrarEditoriales();
  }

  public boolean registerPublisher(Publisher publishers) {
    if (validateNewPublisher(publishers)) {
      editorialDao.insertarEditorial(publishers);
      return true;
    }
    return false;
  }

  public boolean publisherExists(Publisher publisher) {
    Publisher existingPublisher = editorialDao.encontrarEditorialPorId(null);
    return existingPublisher != null;
  }

  private boolean validateNewPublisher(Publisher publisher) {
    return !publisherExists(publisher);
  }

  public Publisher getPublisherById(Long id) {
    return editorialDao.encontrarEditorialPorId(id);
  }

  public boolean removePublisher(Long idEditorial) {
    editorialDao.eliminarEditorial(idEditorial);
    return true;
  }

  public boolean editPublisher(Publisher publisher) {
    editorialDao.actualizarEditorial(publisher);
    return true;
  }
}
