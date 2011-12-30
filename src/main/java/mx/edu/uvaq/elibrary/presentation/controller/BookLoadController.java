/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import mx.edu.uvaq.elibrary.model.business.service.BookService;
import mx.edu.uvaq.elibrary.model.business.service.ServiceException;
import mx.edu.uvaq.elibrary.presentation.Message;
import mx.edu.uvaq.elibrary.presentation.command.BookForm;
import mx.edu.uvaq.elibrary.presentation.upload.FileUploadListener;
import net.sf.json.JSONSerializer;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.io.IOUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author arcesino
 */
public class BookLoadController extends HttpServlet {

  public static final String FORM_NAME = "libroForma";
  private BookService bookService;

  @Override
  public void init() throws ServletException {
    bookService = (BookService) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("librosServicio");
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
    BookForm bookForm = (BookForm) request.getAttribute(FORM_NAME);
    String action = bookForm.getAction();
    if ("nuevo-libro".equals(action)) {
      newBook(request, response);
    } else if ("editar-libro".equals(action)) {
      editBook(request, response);
    } else {
      defaultAction(request, response);
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

  private void newBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    BookForm bookForm = (BookForm) request.getAttribute(FORM_NAME);
    byte[] fileBytes = getFileBytes(bookForm.getFileStream());
    byte[] imageBytes = getFileBytes(bookForm.getImageStream());
    try {
      bookService.createBook(bookForm.getBook(), fileBytes, imageBytes);
      bookForm.addMessage("libro-creado", Message.createInformationMessage("Libro creado", "El libro ha sido creado exitosamente."));
    } catch (ServiceException se) {
      bookForm.addMessage("error-libro", Message.createErrorMessage("Error", "Hubo un error al crear el libro."));
    }
    RequestDispatcher dispatcher = getServletContext().getNamedDispatcher("controlador-libros");
    dispatcher.forward(request, response);
  }

  private byte[] getFileBytes(FileItemStream itemStream) throws IOException {
    byte[] fileBytes = null;
    if (itemStream != null) {
      fileBytes = IOUtils.toByteArray(itemStream.openStream());
    }
    return fileBytes;
  }

  private void editBook(HttpServletRequest request, HttpServletResponse response) {
  }

  private void defaultAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
    FileUploadListener uploadListener = (FileUploadListener) request.getSession(false).getAttribute("uploadListener");
    FileUploadListener.FileUploadStats uploadStats = null;
    if (uploadListener != null) {
      uploadStats = uploadListener.getFileUploadStats();
    }
    response.getWriter().write(JSONSerializer.toJSON(uploadStats).toString());
    response.flushBuffer();
  }
}
