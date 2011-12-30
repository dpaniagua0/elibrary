/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service;

import mx.edu.uvaq.elibrary.domain.User;

/**
 *
 * @author daniel
 */
public interface RegisterService {

  public abstract boolean registerUser(User user, String activationURL);

  String generateActivationCode(User user);

  boolean activateUser(User user);
}
