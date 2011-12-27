/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.uvaq.elibrary.presentacion.FlashScope;
import mx.edu.uvaq.elibrary.presentacion.Mensaje;

/**
 *
 * @author arcesino
 */
public abstract class AbstractController {

  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected FlashScope flash;

  public FlashScope getFlash() {
    return flash;
  }

  public void setFlash(FlashScope flash) {
    this.flash = flash;
  }
  
  private Map<String, Mensaje> messages;
  
  public AbstractController () {
    messages = new HashMap<String, Mensaje>();
  }
  
  protected void defaultAction() {
    try {
      response.getWriter().println("I'm the default action. Overwrite me!");
    } catch (IOException ex) {}
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  public HttpServletResponse getResponse() {
    return response;
  }

  public void setResponse(HttpServletResponse response) {
    this.response = response;
  }

  protected Map<String, Mensaje> getMessages() {
    return messages;
  }

  protected void addMessage(String key, Mensaje message) {
    messages.put(key, message);
  }
  
  protected void addSuccessMessage(String key, String message) {
    addSuccessMessage(key, null, message);
  }
  
  protected void addSuccessMessage(String key, String summary, String detail) {
    messages.put(key, Mensaje.crearMensajeExito(summary, detail));
  }
  
  protected void addInfoMessage(String key, String message) {
    addInfoMessage(key, null, message);
  }
  
  protected void addInfoMessage(String key, String summary, String detail) {
    messages.put(key, Mensaje.crearMensajeInformacion(summary, detail));
  }
  
  protected void addWarningMessage(String key, String message) {
    addWarninigMessage(key, null, message);
  }
  
  protected void addWarninigMessage(String key, String summary, String detail) {
    messages.put(key, Mensaje.crearMensajeAdvertencia(summary, detail));
  }
  
  protected void addErrorMessage(String key, String message) {
    addErrorMessage(key, null, message);
  }
  
  protected void addErrorMessage(String key, String summary, String detail) {
    messages.put(key, Mensaje.crearMensajeError(summary, detail));
  }
  
  protected void renderView(String view, Map<String, Object> model) {
    String viewUrl = buildViewURL(view);
    exportModelToRequest(model);
    request.setAttribute("mensajes", messages);
    RequestDispatcher dispatcher = request.getRequestDispatcher(viewUrl);
    try {
      dispatcher.forward(request, response);
    } catch (Exception ex) {
      // TODO: Handle exception for unknown views.
    }
  }

  private String buildViewURL(String view) {
    StringBuilder sb = new StringBuilder("/WEB-INF/jsp/");
    sb.append(getControllerName());
    sb.append('/');
    sb.append(view);
    sb.append(".jsp");
    return sb.toString();
  }

  private String getControllerName() {
    String controllerClassName = this.getClass().getSimpleName().toLowerCase();
    return controllerClassName.replaceFirst("controller", "");
  }

  private void exportModelToRequest(Map<String, Object> model) {
    for(Map.Entry<String, Object> entry : model.entrySet()) {
      request.setAttribute(entry.getKey(), entry.getValue());
    }
  }
}
