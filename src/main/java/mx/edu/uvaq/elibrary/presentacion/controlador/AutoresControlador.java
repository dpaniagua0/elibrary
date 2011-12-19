/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.uvaq.elibrary.modelo.entidades.Autor;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.AutoresServicio;
import mx.edu.uvaq.elibrary.presentacion.Mensaje;
import mx.edu.uvaq.elibrary.presentacion.UtilidadesControlador;
import mx.edu.uvaq.elibrary.presentacion.forma.AutoresForma;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author daniel
 */
public class AutoresControlador extends HttpServlet {

  private AutoresServicio autoresServicio;
  private String NOMBRE_FORMA = "autoresForma";
  private String VISTA_AUTORES = "vista-autores";

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void init() throws ServletException {
    autoresServicio = (AutoresServicio) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("autorServicio");
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    AutoresForma categoriasForma = UtilidadesControlador.obtenerForm(AutoresForma.class, request);
    request.setAttribute(NOMBRE_FORMA, categoriasForma);

    String vistaSiguiente = ejecutarAccion(request, response);

    if (vistaSiguiente != null) {
      RequestDispatcher rd = getServletContext().getNamedDispatcher(vistaSiguiente);
      rd.forward(request, response);
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

  private String ejecutarAccion(HttpServletRequest request, HttpServletResponse response) {
    String vistaSiguiente = null;
    AutoresForma categoriasForma = (AutoresForma) request.getAttribute(NOMBRE_FORMA);
    String accion = categoriasForma.getAccion();
    if ("defecto".equals(accion)) {
      vistaSiguiente = ejecutarAccionDefecto(request, response);
    } else if ("agregar".equals(accion)) {
      vistaSiguiente = ejecutarAccionAgregar(categoriasForma, request);
    }
    return vistaSiguiente;
  }

  private String ejecutarAccionDefecto(HttpServletRequest request, HttpServletResponse response) {

    String vistaSiguiente = VISTA_AUTORES;
    AutoresForma categoriasForma = (AutoresForma) request.getAttribute(NOMBRE_FORMA);
    List<Autor> autores = autoresServicio.encontrarAutores();
    categoriasForma.setAutores(autores);
    return vistaSiguiente;
  }

  private String ejecutarAccionAgregar(AutoresForma formaAutor, HttpServletRequest request) {
    Autor nuevoAutor = formaAutor.getAutor();
    if (autoresServicio.registrarAutor(nuevoAutor)) {
      formaAutor.agregarMensaje("exito-registro", Mensaje.crearMensajeInformacion("Informacion", "El autor se ha registrado correctamente"));
    } else {
      formaAutor.agregarMensaje("error-registro", Mensaje.crearMensajeError("Error", "El autor ya ha sido registrado"));
    }
    return ejecutarAccionDefecto(request, null);
  }
}
