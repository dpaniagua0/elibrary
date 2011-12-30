/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.RegisterService;
import mx.edu.uvaq.elibrary.presentation.Mensaje;
import mx.edu.uvaq.elibrary.presentation.UtilidadesControlador;
import mx.edu.uvaq.elibrary.presentation.command.RegisterForm;
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
public class RegistroControlador extends HttpServlet {

  private static final String VISTA_REGISTRO = "vista-registro";
  private RegisterService registerService;
  private static final String NOMBRE_FORMA = "formaRegister";

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   *
   * @param request  servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException      if an I/O error occurs
   */
  @Override
  public void init() throws ServletException {
    registerService = (RegisterService) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("registroServicio");
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RegisterForm registerForma = UtilidadesControlador.obtenerForm(RegisterForm.class, request);
    request.setAttribute(NOMBRE_FORMA, registerForma);

    String vistaSiguiente = ejecutarAccion(request, response);

    RequestDispatcher rd = getServletContext().getNamedDispatcher(vistaSiguiente);
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

  private String ejecutarAccion(HttpServletRequest request, HttpServletResponse response) {
    String vistaSiguiente = VISTA_REGISTRO;
    RegisterForm formaRegister = (RegisterForm) request.getAttribute(NOMBRE_FORMA);
    if ("activar".equalsIgnoreCase(formaRegister.getAction())) {
      vistaSiguiente = ejecutarAccionActivar(formaRegister, request);
    } else {
      vistaSiguiente = ejecutarAccionDefecto(formaRegister, request);
    }

    return vistaSiguiente;
  }

  private String ejecutarAccionDefecto(RegisterForm formaRegister, HttpServletRequest request) {
    User nuevoUsuario = formaRegister.getUsuario();
    StringBuffer urlActivacion = request.getRequestURL();
    if (registerService.registerUser(nuevoUsuario, urlActivacion.toString())) {
      formaRegister.addMessage("exito-registro", Mensaje.crearMensajeInformacion("Informacion", "El usuario se ha registrado correctamente"));
    } else {
      formaRegister.addMessage("error-registro", Mensaje.crearMensajeError("Error", "El usuario registrado ya existe"));
    }
    return VISTA_REGISTRO;
  }

  private String ejecutarAccionActivar(RegisterForm formaRegister, HttpServletRequest request) {
    User actualizarUsuario = formaRegister.getUsuario();
    if (registerService.activateUser(actualizarUsuario)) {
      formaRegister.addMessage("exito-activacion", Mensaje.crearMensajeError("Informacion", "Activacion exitosa"));
    } else {
      formaRegister.addMessage("error-activacion", Mensaje.crearMensajeError("Error", "Activacion fallida"));
    }
    return VISTA_REGISTRO;
  }
}
