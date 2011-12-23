/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.uvaq.elibrary.presentacion.Mensaje;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author arcesino
 */
public abstract class AbstractControlador extends HttpServlet {

  // TODO: Quitar estado mutable para que el servlet sea thread-safe.
  private HttpServletRequest request;
  private HttpServletResponse response;
  private String accion;
  private Map<String, Mensaje> mensajes = new HashMap<String, Mensaje>();
  
  /** 
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  private void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    this.request = request;
    this.response = response;
    this.accion = getAccion(request);
    if (StringUtils.isNotBlank(accion)) {
      ejecutarAccion(this.accion);
    } else {
      ejecutarAccionDefecto();
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /** 
   * Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /** 
   * Handles the HTTP <code>POST</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  private String getAccion(HttpServletRequest request) {
    String accion = request.getParameter("accion");
    return StringUtils.deleteWhitespace(accion);
  }

  protected String getAccion() {
    return accion;
  }

  protected HttpServletRequest getRequest() {
    return request;
  }

  protected HttpServletResponse getResponse() {
    return response;
  }
  
  protected void desplegarVista(String vista, Map modelo) {
      request.setAttribute("modelo", modelo);
      request.setAttribute("mensajes", mensajes);
      RequestDispatcher rd = getServletContext().getNamedDispatcher(vista);
      try {
        rd.forward(request, response);
      } catch(Exception e){
        // TODO: Implementar manejo de excepción en el despliegue.
      } 
  }
  
  protected void accionNoEncontrada() {
    getResponse().setStatus(HttpServletResponse.SC_NOT_FOUND);
  }
  
  protected Map<String, Mensaje> getMensajes() {
    return mensajes;
  }

  protected void agregarMensaje(String clave, Mensaje mensaje) {
    mensajes.put(clave, mensaje);
  }

  public abstract void ejecutarAccion(String accion);
  public abstract void ejecutarAccionDefecto();
}
