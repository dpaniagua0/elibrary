/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio;

import java.util.List;
import mx.edu.uvaq.elibrary.modelo.entidades.Editorial;

/**
 *
 * @author daniel
 */
public interface EditorialesServicio {

  List<Editorial> encontrarEditoriales();

  public boolean registrarEditorial(Editorial editorial);
}
