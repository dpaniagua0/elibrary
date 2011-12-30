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
import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.EmailService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author arcesino
 */
public class EmailServiceImpl implements EmailService {

  private JavaMailSender mailSender;
  private VelocityEngine velocityEngine;

  public void setMailSender(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void setVelocityEngine(VelocityEngine velocityEngine) {
    this.velocityEngine = velocityEngine;
  }

  public void sendAccountActivationMail(User user, String activationURL) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper mime = new MimeMessageHelper(message, true);
      mime.setFrom("admin@uvaq.edu.mx");
      mime.setSubject("UVAQ [elibrary]: Activación de cuenta");
      mime.setTo(user.getEmail());
      Map<String, Object> data = new HashMap<String, Object>();
      data.put("user", user);
      data.put("activationURL", activationURL);
      String messageContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mx/edu/uvaq/elibrary/correo/activacion-cuenta.vm", data);
      mime.setText(messageContent, true);
      mailSender.send(message);
    } catch (MessagingException ex) {
      Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
