/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.UserService;
import mx.edu.uvaq.elibrary.presentation.command.UsersForm;
import mx.edu.uvaq.elibrary.presentation.controller.util.ControllerUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author daniel
 */
public class UsersController extends HttpServlet {

  private final static String NOMBRE_FORMA = "usuariosForma";
  private UserService userService;

  @Override
  public void init() throws ServletException {
    userService = (UserService) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("usuariosServicio");
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
    UsersForm usersForm = ControllerUtils.getForm(UsersForm.class, request);
    request.setAttribute(NOMBRE_FORMA, usersForm);

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

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private String executeAction(HttpServletRequest request, HttpServletResponse response) {
    String nextView = "vista-usuarios";
    UsersForm usersForm = (UsersForm) request.getAttribute(NOMBRE_FORMA);
    List<User> users = userService.getUsers();
    usersForm.setUsers(users);
    return nextView;
  }
}
