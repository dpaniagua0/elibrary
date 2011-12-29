/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import mx.edu.uvaq.elibrary.domain.Usuario;
import mx.edu.uvaq.elibrary.model.business.service.CorreoServicio;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author arcesino
 */
public class CorreoServicioImpl implements CorreoServicio {

  private JavaMailSender enviadorCorreo;
  private VelocityEngine motorVelocity;

  public void setEnviadorCorreo(JavaMailSender enviadorCorreo) {
    this.enviadorCorreo = enviadorCorreo;
  }

  public void setMotorVelocity(VelocityEngine motorVelocity) {
    this.motorVelocity = motorVelocity;
  }

  public void enviarCorreoActivacionCuenta(Usuario usuario, String urlActivacion) {
    try {
      MimeMessage mensaje = enviadorCorreo.createMimeMessage();
      MimeMessageHelper mime = new MimeMessageHelper(mensaje, true);
      mime.setFrom("admin@uvaq.edu.mx");
      mime.setSubject("UVAQ [elibrary]: Activación de cuenta");
      mime.setTo(usuario.getCorreoElectronico());
      Map<String, Object> datos = new HashMap<String, Object>();
      datos.put("usuario", usuario);
      datos.put("urlActivacion", urlActivacion);
      String contenidoMensaje = VelocityEngineUtils.mergeTemplateIntoString(motorVelocity, "mx/edu/uvaq/elibrary/correo/activacion-cuenta.vm", datos);
      mime.setText(contenidoMensaje, true);
      enviadorCorreo.send(mensaje);
    } catch (MessagingException ex) {
      Logger.getLogger(CorreoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
