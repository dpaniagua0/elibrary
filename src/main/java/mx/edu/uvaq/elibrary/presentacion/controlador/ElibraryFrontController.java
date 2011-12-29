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
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
    routeRequest(request, response);
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

  private String getFixedPathInfo(HttpServletRequest request) {
    String pathInfo = request.getPathInfo();
    if (StringUtils.isNotEmpty(pathInfo)) {
      pathInfo = pathInfo.substring(1);
      if (pathInfo.endsWith("/")) {
        pathInfo = pathInfo.substring(0, pathInfo.length() - 1);
      }
    }
    return pathInfo;
  }

  private void routeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String requestURL = getRequestURL(request);
    String action = null;
    String controllerURL = null;
    AbstractController controller = null;
    try {
      controller = (AbstractController) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(requestURL.replaceAll("/", "_"));
    } catch(NoSuchBeanDefinitionException exc) {}
    if (controller != null) {
      initController(controller, request, response);
      controllerURL = requestURL;
      action = "defaultAction";
    } else {
      controllerURL = request.getServletPath();
      action = getFixedPathInfo(request);
      if (action.contains("/")) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
    }
    if (controller == null) {
      System.out.println("Getting controller");
      controller = getController(controllerURL, request, response);
    }
    executeAction(controller, action);
  }

  private String getRequestURL(HttpServletRequest request) {
    String servletPath = request.getServletPath();
    String pathInfo = request.getPathInfo();
    String requestURL = StringUtils.defaultString(servletPath) + StringUtils.defaultString(pathInfo);
    if (requestURL.endsWith("/")) {
      requestURL = requestURL.substring(0, requestURL.length() - 1);
    }
    return requestURL;
  }

  private String getAction(HttpServletRequest request) {
    String requestURL = getRequestURL(request);
    int lastSlashIndex = requestURL.lastIndexOf('/');
    return requestURL.substring(lastSlashIndex + 1);
  }

  private AbstractController getController(String controllerURL, HttpServletRequest request, HttpServletResponse response) throws IOException {
    String controllerId = controllerURL.replace('/', '_');
    Object controllerBean = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(controllerId);
    if (controllerBean == null) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, "Controlador no encontrado!");
    }
    if (!(controllerBean instanceof AbstractController)) {
      throw new RuntimeException("Matched object is not a valid controller class! " + controllerBean.getClass().getName());
    }
    AbstractController controller = (AbstractController) controllerBean;
    return initController(controller, request, response);
  }

  private AbstractController initController(AbstractController controller, HttpServletRequest request, HttpServletResponse response) {
    controller.setRequest(request);
    controller.setResponse(response);
    return controller;
  }

  private String extractControllerURL(HttpServletRequest request) {
    String requestURL = getRequestURL(request);
    int lastSlashIndex = requestURL.lastIndexOf('/');
    return requestURL.substring(0, lastSlashIndex);
  }

  private void executeAction(AbstractController controller, String action) {
    Method actionMethod = getActionMethod(controller, action);
    ReflectionUtils.invokeMethod(actionMethod, controller);
  }

  private Method getActionMethod(AbstractController controller, String action) {
    Class controllerClass = controller.getClass();
    Method actionMethod = ReflectionUtils.findMethod(controllerClass, action);
    if (actionMethod == null) {
      try {
        controller.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND, "Accion no encontrada!");
      } catch (IOException ex) {
      }
    }
    return actionMethod;
  }
}
