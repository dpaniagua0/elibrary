/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller.util;

/**
 * @author arcesino
 */
public class URLMapping {

  private final String controller;
  private final String action;

  public URLMapping(String controller, String action) {
    this.controller = controller;
    this.action = action;
  }

  public String getAction() {
    return action;
  }

  public String getController() {
    return controller;
  }
}
