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
public interface EditorialesServicio {

  List<Publisher> getEditoriales();

  public boolean registrarEditorial(Publisher editorial);

  public Publisher getEditorialPorId(Long ideditorial);

  public boolean borrarEditorial(Long id);

  public boolean modificarEditorial(Publisher editorial);
}
