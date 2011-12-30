/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import mx.edu.uvaq.elibrary.domain.User;

/**
 *
 * @author arcesino
 */
public interface EmailService {

  public abstract void sendAccountActivationMail(User user, String activationURL);
}
