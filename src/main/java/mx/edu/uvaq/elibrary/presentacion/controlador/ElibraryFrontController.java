/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.controlador;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.uvaq.elibrary.presentacion.SpringWebApplicationContext;
import mx.edu.uvaq.elibrary.presentacion.URLMapper;
import mx.edu.uvaq.elibrary.presentacion.URLMapping;
import mx.edu.uvaq.elibrary.presentacion.exception.WebResourceNotFoundException;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author arcesino
 */
public class ElibraryFrontController extends HttpServlet {

  /** 
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      System.out.println("About to mapping URL");
      URLMapping urlMapping = URLMapper.mapRequest(request);
      System.out.println("URLMapping: " + urlMapping);
      routeRequest(urlMapping, request, response);
    } catch (WebResourceNotFoundException exc) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getRequestURI());
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

  /** 
   * Returns a short description of the servlet.
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void routeRequest(URLMapping urlMapping, HttpServletRequest request, HttpServletResponse response) throws IOException {
    AbstractController controller = getController(urlMapping.getController(), request, response);
    executeAction(controller, urlMapping.getAction());
  }

  private AbstractController getController(String controllerURL, HttpServletRequest request, HttpServletResponse response) {
    String controllerId = controllerURL.replace('/', '_');
    Object controllerBean = SpringWebApplicationContext.getBean(controllerId);
    if (!(controllerBean instanceof AbstractController)) {
      throw new WebResourceNotFoundException();
    }
    AbstractController controller = (AbstractController) controllerBean;
    return initController(controller, request, response);
  }

  private AbstractController initController(AbstractController controller, HttpServletRequest request, HttpServletResponse response) {
    controller.setRequest(request);
    controller.setResponse(response);
    return controller;
  }

  private void executeAction(AbstractController controller, String action) {
    Method actionMethod = getActionMethod(controller, action);
    ReflectionUtils.invokeMethod(actionMethod, controller);
  }

  private Method getActionMethod(AbstractController controller, String action) {
    Class controllerClass = controller.getClass();
    Method actionMethod = ReflectionUtils.findMethod(controllerClass, action);
    if (actionMethod == null) {
      throw new WebResourceNotFoundException();
    }
    return actionMethod;
  }
}
