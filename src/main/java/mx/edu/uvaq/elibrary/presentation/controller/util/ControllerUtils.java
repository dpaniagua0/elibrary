/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.edu.uvaq.elibrary.presentation.controller.util;

import mx.edu.uvaq.elibrary.presentation.command.AbstractForm;
import mx.edu.uvaq.elibrary.presentation.controller.UsersController;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author arcesino
 */
public final class ControllerUtils {
  public static <T extends AbstractForm> T getForm(Class<T> claseForma, HttpServletRequest request) {
    // Instanciación de JavaBean dinámicamente.
    T form = null;
    try {
      form = claseForma.newInstance();
      BeanUtils.populate(form, request.getParameterMap());
    } catch (Exception ex) {
      Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
    }

    return form;
  }
}
