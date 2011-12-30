/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.Date;
import mx.edu.uvaq.elibrary.model.business.service.RegisterService;
import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.EmailService;
import mx.edu.uvaq.elibrary.model.persistence.dao.UsuarioDao;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author daniel
 */
public class RegisterServiceImpl implements RegisterService {

  private UsuarioDao usuarioDao;
  private EmailService emailService;

  public void setUsuarioDao(UsuarioDao usuarioDao) {
    this.usuarioDao = usuarioDao;
  }

  public void setEmailService(EmailService emailService) {
    this.emailService = emailService;
  }

  public boolean registerUser(User user, String activationURL) {
    if (validateNewUser(user)) {
      String activationCode = generateActivationCode(user);
      user.setActivationCode(activationCode);
      usuarioDao.insertarUsuario(user);
      emailService.sendAccountActivationMail(user, activationURL);
      return true;
    }
    return false;
  }

  public boolean userExists(User user) {
    User existingUser = usuarioDao.encontrarUsuario(user.getEmail());

    return existingUser != null;
  }

  private boolean validateNewUser(User nuevoUsuario) {
    return !userExists(nuevoUsuario);
  }

  public String generateActivationCode(User user) {
    String data = String.format("%s%s%s%s", user.getEmail(), user.getName(),
            user.getLastName(), new Date().getTime());

    byte[] activationCode = DigestUtils.sha256(data);

    return Base64.encodeBase64URLSafeString(activationCode);
  }

  public boolean activateUser(User user) {
    if (!validateNewUser(user)) {
      usuarioDao.actualizarUsuario(user.getEmail());
      usuarioDao.asignarRolUsuario(user, "estudiante");
      return true;
    }
    return false;

  }
}
