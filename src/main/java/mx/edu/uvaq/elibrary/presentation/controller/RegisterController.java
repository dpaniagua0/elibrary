/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.RegisterService;
import mx.edu.uvaq.elibrary.presentation.Message;
import mx.edu.uvaq.elibrary.presentation.command.RegisterForm;
import mx.edu.uvaq.elibrary.presentation.controller.util.ControllerUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author daniel
 */
public class RegisterController extends HttpServlet {

  private static final String REGISTER_VIEW = "vista-registro";
  private RegisterService registerService;
  private static final String FORM_NAME = "registerForm";

  @Override
  public void init() throws ServletException {
    registerService = (RegisterService) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("registroServicio");
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
    RegisterForm registerForm = ControllerUtils.getForm(RegisterForm.class, request);
    request.setAttribute(FORM_NAME, registerForm);

    String nextView = executeAction(request, response);

    RequestDispatcher rd = getServletContext().getNamedDispatcher(nextView);
    rd.forward(request, response);
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

  private String executeAction(HttpServletRequest request, HttpServletResponse response) {
    String nextView = REGISTER_VIEW;
    RegisterForm registerForm = (RegisterForm) request.getAttribute(FORM_NAME);
    if ("activar".equalsIgnoreCase(registerForm.getAction())) {
      nextView = activateUser(registerForm, request);
    } else {
      nextView = defaultAction(registerForm, request);
    }

    return nextView;
  }

  private String defaultAction(RegisterForm registerForm, HttpServletRequest request) {
    User newUser = registerForm.getUsuario();
    StringBuffer activationURL = request.getRequestURL();
    if (registerService.registerUser(newUser, activationURL.toString())) {
      registerForm.addMessage("exito-registro", Message.createInformationMessage("Informacion", "El usuario se ha registrado correctamente"));
    } else {
      registerForm.addMessage("error-registro", Message.createErrorMessage("Error", "El usuario registrado ya existe"));
    }
    return REGISTER_VIEW;
  }

  private String activateUser(RegisterForm registerForm, HttpServletRequest request) {
    User user = registerForm.getUsuario();
    if (registerService.activateUser(user)) {
      registerForm.addMessage("exito-activacion", Message.createErrorMessage("Informacion", "Activacion exitosa"));
    } else {
      registerForm.addMessage("error-activacion", Message.createErrorMessage("Error", "Activacion fallida"));
    }
    return REGISTER_VIEW;
  }
}
