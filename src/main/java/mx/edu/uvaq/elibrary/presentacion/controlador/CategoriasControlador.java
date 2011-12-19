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
import mx.edu.uvaq.elibrary.modelo.entidades.Categoria;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.CategoriaServicio;
import mx.edu.uvaq.elibrary.presentacion.Mensaje;
import mx.edu.uvaq.elibrary.presentacion.UtilidadesControlador;
import mx.edu.uvaq.elibrary.presentacion.forma.CategoriasForma;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author daniel
 */
public class CategoriasControlador extends HttpServlet {

  private CategoriaServicio categoriasServicio;
  private String NOMBRE_FORMA = "categoriasForma";
  private String VISTA_CATEGORIAS = "vista-categorias";

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void init() throws ServletException {
    categoriasServicio = (CategoriaServicio) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("categoriaServicio");
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    CategoriasForma categoriasForma = UtilidadesControlador.obtenerForm(CategoriasForma.class, request);
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
    CategoriasForma categoriasForma = (CategoriasForma) request.getAttribute(NOMBRE_FORMA);
    String accion = categoriasForma.getAccion();
    if ("defecto".equals(accion)) {
      vistaSiguiente = ejecutarAccionDefecto(request, response);
    } else if ("agregar".equals(accion)) {
      vistaSiguiente = ejecutarAccionAgregar(categoriasForma, request);
    }
    return vistaSiguiente;
  }

  private String ejecutarAccionDefecto(HttpServletRequest request, HttpServletResponse response) {

    String vistaSiguiente = VISTA_CATEGORIAS;
    CategoriasForma categoriasForma = (CategoriasForma) request.getAttribute(NOMBRE_FORMA);
    List<Categoria> categorias = categoriasServicio.encontrarCategorias();
    categoriasForma.setCategorias(categorias);
    return vistaSiguiente;
  }

  private String ejecutarAccionAgregar(CategoriasForma categoriaForma, HttpServletRequest request) {
    Categoria categoria = categoriaForma.getCategoria();
    if (categoriasServicio.agregarCategoria(categoria)) {
      categoriaForma.agregarMensaje("exito-registro", Mensaje.crearMensajeError("Informacion", "La cagetoria se ha registrado correctamente"));
    } else {
      categoriaForma.agregarMensaje("error-registro", Mensaje.crearMensajeError("Error", "Esta editorial ya existe"));
    }
    return ejecutarAccionDefecto(request, null);
  }
}
