/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.RegistroServicio;
import mx.edu.uvaq.elibrary.presentation.Mensaje;
import mx.edu.uvaq.elibrary.presentation.UtilidadesControlador;
import mx.edu.uvaq.elibrary.presentation.command.RegistroForma;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author daniel
 */
public class RegistroControlador extends HttpServlet {

  private static final String VISTA_REGISTRO = "vista-registro";
  private RegistroServicio registroServicio;
  private static final String NOMBRE_FORMA = "formaRegistro";

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void init() throws ServletException {
    registroServicio = (RegistroServicio) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("registroServicio");
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    RegistroForma registroForma = UtilidadesControlador.obtenerForm(RegistroForma.class, request);
    request.setAttribute(NOMBRE_FORMA, registroForma);

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

  private String ejecutarAccion(HttpServletRequest request, HttpServletResponse response) {
    String vistaSiguiente = VISTA_REGISTRO;
    RegistroForma formaRegistro = (RegistroForma) request.getAttribute(NOMBRE_FORMA);
    if ("activar".equalsIgnoreCase(formaRegistro.getAccion())) {
      vistaSiguiente = ejecutarAccionActivar(formaRegistro, request);
    } else {
      vistaSiguiente = ejecutarAccionDefecto(formaRegistro, request);
    }

    return vistaSiguiente;
  }

  private String ejecutarAccionDefecto(RegistroForma formaRegistro, HttpServletRequest request) {
    User nuevoUsuario = formaRegistro.getUsuario();
    StringBuffer urlActivacion = request.getRequestURL();
    if (registroServicio.registrarUsuario(nuevoUsuario, urlActivacion.toString())) {
      formaRegistro.agregarMensaje("exito-registro", Mensaje.crearMensajeInformacion("Informacion", "El usuario se ha registrado correctamente"));
    } else {
      formaRegistro.agregarMensaje("error-registro", Mensaje.crearMensajeError("Error", "El usuario registrado ya existe"));
    }
    return VISTA_REGISTRO;
  }

  private String ejecutarAccionActivar(RegistroForma formaRegistro, HttpServletRequest request) {
    User actualizarUsuario = formaRegistro.getUsuario();
    if (registroServicio.activarUsuario(actualizarUsuario)) {
      formaRegistro.agregarMensaje("exito-activacion", Mensaje.crearMensajeError("Informacion", "Activacion exitosa"));
    } else {
      formaRegistro.agregarMensaje("error-activacion", Mensaje.crearMensajeError("Error", "Activacion fallida"));
    }
    return VISTA_REGISTRO;
  }
}
