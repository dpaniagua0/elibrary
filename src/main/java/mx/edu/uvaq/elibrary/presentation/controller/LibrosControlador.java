/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.business.service.BookService;
import mx.edu.uvaq.elibrary.presentation.UtilidadesControlador;
import mx.edu.uvaq.elibrary.presentation.command.LibrosForma;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import org.springframework.web.context.support.WebApplicationContextUtils;

/** 
 *
 * @author daniel
 */
public class LibrosControlador extends HttpServlet {

  private static final String VISTA_INICIO = "vista-inicio";
  private static final String NOMBRE_FORMA = "librosForma";
  
  private BookService bookService;

  @Override
  public void init() throws ServletException {
    bookService = (BookService) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("librosServicio");
  }

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    LibrosForma librosForma = UtilidadesControlador.obtenerForm(LibrosForma.class, request);
    request.setAttribute(NOMBRE_FORMA, librosForma);

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

  private String ejecutarAccion(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String vistaSiguiente = null;
    LibrosForma librosForma = (LibrosForma) request.getAttribute(NOMBRE_FORMA);
    String accion = librosForma.getAccion();
    if ("defecto".equals(accion)) {
      vistaSiguiente = ejecutarAccionDefecto(request, response);
    } else if ("buscar-libro".equals(accion)) {
      vistaSiguiente = ejecutarAccionBuscarLibro(request, response);
    }

    return vistaSiguiente;
  }

  private String ejecutarAccionDefecto(HttpServletRequest request, HttpServletResponse response) {
    String vistaSiguiente = VISTA_INICIO;
    if (request.isUserInRole("administrador")) {
      vistaSiguiente = "vista-inicio-administracion";
    }
    LibrosForma librosForma = (LibrosForma) request.getAttribute(NOMBRE_FORMA);
    List<Book> libros = bookService.getBooks();
    librosForma.setLibros(libros);
    return vistaSiguiente;
  }

  private String ejecutarAccionBuscarLibro(HttpServletRequest request, HttpServletResponse response) throws IOException {
    LibrosForma librosForma = (LibrosForma) request.getAttribute(NOMBRE_FORMA);
    int id = librosForma.getId();

    Book libro = bookService.getBookById(id);
    JsonConfig config = new JsonConfig();
    config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());

    response.setContentType("application/json");
    response.getWriter().write(JSONSerializer.toJSON(libro, config).toString());
    response.flushBuffer();

    return null;
  }
}
