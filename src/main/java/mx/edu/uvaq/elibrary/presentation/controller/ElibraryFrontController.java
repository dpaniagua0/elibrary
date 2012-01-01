/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import mx.edu.uvaq.elibrary.presentation.controller.util.URLMapper;
import mx.edu.uvaq.elibrary.presentation.controller.util.URLMapping;
import mx.edu.uvaq.elibrary.presentation.exception.WebResourceNotFoundException;
import mx.edu.uvaq.elibrary.web.FlashScope;
import mx.edu.uvaq.elibrary.web.SpringWebApplicationContext;
import org.springframework.beans.PropertyValues;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author arcesino
 */
public class ElibraryFrontController extends HttpServlet {

  private Validator validator;

  @Override
  public void init() throws ServletException {
    validator = SpringWebApplicationContext.getBean("validatorImpl", Validator.class);
  }

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   *
   * @param request  servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException      if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      URLMapping urlMapping = URLMapper.mapRequest(request);
      routeRequest(urlMapping, request, response);
    } catch (WebResourceNotFoundException exc) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getRequestURI());
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request  servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException      if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request  servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException      if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
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
    controller.setFlash(new FlashScope(request));
    return controller;
  }

  private void executeAction(AbstractController controller, String action) {
    Method actionMethod = getActionMethod(controller, action);
    Object command = getCommand(actionMethod);
    BindingResult result = null;
    if (command != null) {
      result = processCommand(command, controller.getRequest());
    }
    Class[] parameterTypes = actionMethod.getParameterTypes();
    switch (parameterTypes.length) {
      case 0:
        ReflectionUtils.invokeMethod(actionMethod, controller);
        break;
      case 1:
        ReflectionUtils.invokeMethod(actionMethod, controller, command);
        break;
      default:
        ReflectionUtils.invokeMethod(actionMethod, controller, command, result);
    }
  }

  private Method getActionMethod(AbstractController controller, String action) {
    Method actionMethod = findActionMethod(controller, action);
    if (actionMethod == null) {
      throw new WebResourceNotFoundException();
    }
    return actionMethod;
  }

  private Method findActionMethod(AbstractController controller, String action) {
    Method actionMethod = null;
    Class controllerClass = controller.getClass();
    Method[] controllerMethods = ReflectionUtils.getAllDeclaredMethods(controllerClass);
    for (Method controllerMethod : controllerMethods) {
      if (controllerMethod.getName().equals(action)) {
        actionMethod = controllerMethod;
        break;
      }
    }
    return actionMethod;
  }

  private Object getCommand(Method actionMethod) {
    Object command = null;
    Class[] parametersTypes = actionMethod.getParameterTypes();
    if (parametersTypes.length > 0) {
      Class commandType = parametersTypes[0];
      Constructor commandConstructor = ClassUtils.getConstructorIfAvailable(commandType);
      if (commandConstructor != null) {
        try {
          command = commandConstructor.newInstance();
        } catch (Exception e) {
          throw new RuntimeException("Cannot instantiate command object of type " + commandType.getName(), e);
        }
      } else {
        throw new RuntimeException("Command object of type " + commandType.getName() + "is not a bean!");
      }
    }
    return command;
  }

  private BindingResult processCommand(Object command, HttpServletRequest request) {
    PropertyValues propertyValues = new ServletRequestParameterPropertyValues(request);
    DataBinder binder = new DataBinder(command, command.getClass().getSimpleName());
    binder.setValidator(validator);
    binder.bind(propertyValues);
    binder.validate();
    return binder.getBindingResult();
  }
}
