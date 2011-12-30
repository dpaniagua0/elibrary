/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.domain.User;

/**
 * @author daniel
 */
public class RegisterForm extends AbstractForm {

  private String name;
  private String lastName;
  private String email;
  private String password;
  private String activationCode;

  public String getActivationCode() {
    return activationCode;
  }

  public void setActivationCode(String activationCode) {
    this.activationCode = activationCode;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String contraseña) {
    this.password = contraseña;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getUsuario() {
    User user = new User();
    user.setName(name);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(password);
    user.setActivationCode(activationCode);
    return user;
  }
}
