/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.presentation.Message;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author daniel
 */
public abstract class AbstractForm {
  private Map<String, Message> messages;
  private String action;

  public AbstractForm() {
    messages = new LinkedHashMap<String, Message>();
    action = "defecto";
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Map<String, Message> getMessages() {
    return messages;
  }

  public void addMessage(String key, Message message) {
    messages.put(key, message);
  }
}
