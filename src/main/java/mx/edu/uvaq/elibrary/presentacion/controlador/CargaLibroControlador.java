/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.controlador;

import mx.edu.uvaq.elibrary.modelo.negocio.servicio.ServiceException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.LibrosServicio;
import mx.edu.uvaq.elibrary.presentacion.Mensaje;
import mx.edu.uvaq.elibrary.presentacion.forma.LibroForma;
import mx.edu.uvaq.elibrary.presentacion.io.FileUploadListener;
import net.sf.json.JSONSerializer;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.io.IOUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author arcesino
 */
public class CargaLibroControlador extends HttpServlet {

  public static final String NOMBRE_FORMA = "libroForma";
  private LibrosServicio librosServicio;

  @Override
  public void init() throws ServletException {
    librosServicio = (LibrosServicio) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("librosServicio");
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
    LibroForma libroForma = (LibroForma) request.getAttribute(NOMBRE_FORMA);
    String accion = libroForma.getAccion();
    if ("nuevo-libro".equals(accion)) {
      procesarAccionNuevoLibro(request, response);
    } else if ("editar-libro".equals(accion)) {
      procesarAccionEditarLibro(request, response);
    } else {
      procesarAccionPorDefecto(request, response);
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

  private void procesarAccionNuevoLibro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    LibroForma libroForma = (LibroForma) request.getAttribute(NOMBRE_FORMA);
    byte[] archivo = obtenerBytesAchivo(libroForma.getArchivo());
    byte[] imagen = obtenerBytesAchivo(libroForma.getImagen());
    try {
      librosServicio.crearLibro(libroForma.getLibro(), archivo, imagen);
      libroForma.agregarMensaje("libro-creado", Mensaje.crearMensajeInformacion("Libro creado", "El libro ha sido creado exitosamente."));
    } catch(ServiceException se) {
      libroForma.agregarMensaje("error-libro", Mensaje.crearMensajeError("Error", "Hubo un error al crear el libro."));
    }
    RequestDispatcher dispatcher = getServletContext().getNamedDispatcher("controlador-libros");
    dispatcher.forward(request, response);
  }

  private byte[] obtenerBytesAchivo(FileItemStream itemStream) throws IOException {
    byte[] bytesArchivo = null;
    if (itemStream != null) {
      bytesArchivo = IOUtils.toByteArray(itemStream.openStream());
    }
    return bytesArchivo;
  }

  private void procesarAccionEditarLibro(HttpServletRequest request, HttpServletResponse response) {
  }

  private void procesarAccionPorDefecto(HttpServletRequest request, HttpServletResponse response) throws IOException {
    FileUploadListener uploadListener = (FileUploadListener) request.getSession(false).getAttribute("uploadListener");
    FileUploadListener.FileUploadStats uploadStats = null;
    if (uploadListener != null) {
      uploadStats = uploadListener.getFileUploadStats();
    }
    response.getWriter().write(JSONSerializer.toJSON(uploadStats).toString());
    response.flushBuffer();
  }
}
