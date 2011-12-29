/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.uvaq.elibrary.domain.Usuario;
import mx.edu.uvaq.elibrary.model.business.service.UsuariosServicio;
import mx.edu.uvaq.elibrary.presentation.UtilidadesControlador;
import mx.edu.uvaq.elibrary.presentation.command.UsuariosForma;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author daniel
 */
public class UsuariosControlador extends HttpServlet {

  private final static String NOMBRE_FORMA = "usuariosForma";
  private UsuariosServicio usuariosServicio;

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void init() throws ServletException {
    usuariosServicio = (UsuariosServicio) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("usuariosServicio");
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    UsuariosForma usuariosForma = UtilidadesControlador.obtenerForm(UsuariosForma.class, request);
    request.setAttribute(NOMBRE_FORMA, usuariosForma);

    String vistaSiguiente = ejecutarAccion(request, response);

    RequestDispatcher rd = getServletContext().getNamedDispatcher(vistaSiguiente);
    rd.forward(request, response);
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

  private String ejecutarAccion(HttpServletRequest request, HttpServletResponse response) {
    String vistaSiguiente = "vista-usuarios";
    UsuariosForma usuariosForma = (UsuariosForma) request.getAttribute(NOMBRE_FORMA);
    List<Usuario> usuarios = usuariosServicio.recuperarUsuarios();
    usuariosForma.setUsuarios(usuarios);
    return vistaSiguiente;
  }
}
