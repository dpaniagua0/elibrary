/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio.impl;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Editorial;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.EditorialesServicio;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.EditorialDao;

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

  public List<Editorial> encontrarEditoriales() {
    return editorialDao.encontrarEditoriales();
  }

  public boolean registrarEditorial(Editorial editorial) {
    if (validarNuevaEditorial(editorial)) {
      editorialDao.insertarEditorial(editorial);
      return true;
    }
    return false;
  }

  public boolean existeEditorial(Editorial nuevaEditorial) {
    Editorial editorial = editorialDao.encontrarEditorial(nuevaEditorial.getNombre());
    return editorial != null;
  }

  private boolean validarNuevaEditorial(Editorial nuevaEditorial) {
    return !existeEditorial(nuevaEditorial);
  }
}
