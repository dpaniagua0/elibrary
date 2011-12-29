/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.edu.uvaq.elibrary.presentation;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import mx.edu.uvaq.elibrary.presentation.controller.UsuariosControlador;
import mx.edu.uvaq.elibrary.presentation.command.AbstractForma;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author arcesino
 */
public final class UtilidadesControlador {
  public static <T extends AbstractForma> T obtenerForm(Class<T> claseForma, HttpServletRequest request) {
    // Instanciación de JavaBean dinámicamente.
    T form = null;
    try {
      form = claseForma.newInstance();
      BeanUtils.populate(form, request.getParameterMap());
    } catch (Exception ex) {
      Logger.getLogger(UsuariosControlador.class.getName()).log(Level.SEVERE, null, ex);
    }

    return form;
  }
}
