/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.model.business.service.BookService;
import mx.edu.uvaq.elibrary.presentation.command.BooksForm;
import mx.edu.uvaq.elibrary.presentation.controller.util.ControllerUtils;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author daniel
 */
public class BooksController extends HttpServlet {

  private static final String HOME_VIEW = "vista-inicio";
  private static final String FORM_NAME = "librosForma";

  private BookService bookService;

  @Override
  public void init() throws ServletException {
    bookService = (BookService) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("bookService");
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
    BooksForm booksForm = ControllerUtils.getForm(BooksForm.class, request);
    request.setAttribute(FORM_NAME, booksForm);

    String nextView = executeAction(request, response);

    if (nextView != null) {
      RequestDispatcher rd = getServletContext().getNamedDispatcher(nextView);
      rd.forward(request, response);
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

  private String executeAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String nextView = null;
    BooksForm booksForm = (BooksForm) request.getAttribute(FORM_NAME);
    String action = booksForm.getAction();
    if ("defecto".equals(action)) {
      nextView = defaultAction(request, response);
    } else if ("buscar-libro".equals(action)) {
      nextView = findBook(request, response);
    }

    return nextView;
  }

  private String defaultAction(HttpServletRequest request, HttpServletResponse response) {
    String nextView = HOME_VIEW;
    if (request.isUserInRole("administrador")) {
      nextView = "vista-inicio-administracion";
    }
    BooksForm booksForm = (BooksForm) request.getAttribute(FORM_NAME);
    List<Book> books = bookService.getBooks();
    booksForm.setBooks(books);
    return nextView;
  }

  private String findBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
    BooksForm booksForm = (BooksForm) request.getAttribute(FORM_NAME);
    int id = booksForm.getId();

    Book book = bookService.getBookById(id);
    JsonConfig config = new JsonConfig();
    config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());

    response.setContentType("application/json");
    response.getWriter().write(JSONSerializer.toJSON(book, config).toString());
    response.flushBuffer();

    return null;
  }
}
