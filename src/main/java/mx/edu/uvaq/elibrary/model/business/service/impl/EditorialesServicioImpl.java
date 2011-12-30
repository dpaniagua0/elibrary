/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Publisher;
import mx.edu.uvaq.elibrary.model.business.service.EditorialesServicio;
import mx.edu.uvaq.elibrary.model.persistence.dao.EditorialDao;

/**
 *
 * @author daniel
 */
public class EditorialesServicioImpl implements EditorialesServicio {

  private EditorialDao editorialDao;

  public EditorialDao getEditorialDao() {
    return editorialDao;
  }

  public void setEditorialDao(EditorialDao editorialDao) {
    this.editorialDao = editorialDao;
  }

  public List<Publisher> getEditoriales() {
    return editorialDao.encontrarEditoriales();
  }

  public boolean registrarEditorial(Publisher editorial) {
    if (validarNuevaEditorial(editorial)) {
      editorialDao.insertarEditorial(editorial);
      return true;
    }
    return false;
  }

  public boolean existeEditorial(Publisher nuevaEditorial) {
    Publisher editorial = editorialDao.encontrarEditorialPorId(null);
    return editorial != null;
  }

  private boolean validarNuevaEditorial(Publisher nuevaEditorial) {
    return !existeEditorial(nuevaEditorial);
  }

  public Publisher getEditorialPorId(Long idEditorial) {
    return editorialDao.encontrarEditorialPorId(idEditorial);
  }

  public boolean borrarEditorial(Long idEditorial) {
    editorialDao.eliminarEditorial(idEditorial);
    return true;
  }

  public boolean modificarEditorial(Publisher editorial) {
    editorialDao.actualizarEditorial(editorial);
    return true;
  }
}
