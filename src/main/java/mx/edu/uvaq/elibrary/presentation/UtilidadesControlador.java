/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.edu.uvaq.elibrary.presentation;

import mx.edu.uvaq.elibrary.presentation.command.AbstractForm;
import mx.edu.uvaq.elibrary.presentation.controller.UsuariosControlador;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author arcesino
 */
public final class UtilidadesControlador {
  public static <T extends AbstractForm> T obtenerForm(Class<T> claseForma, HttpServletRequest request) {
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
