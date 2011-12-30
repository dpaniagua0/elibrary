/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.web.filter;

import mx.edu.uvaq.elibrary.presentation.command.BookForm;
import mx.edu.uvaq.elibrary.presentation.controller.BookLoadController;
import mx.edu.uvaq.elibrary.presentation.controller.BooksController;
import mx.edu.uvaq.elibrary.presentation.controller.util.ControllerUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.time.DateUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author arcesino
 */
public class BookFormFilter implements Filter {

  private static final boolean debug = true;
  // The filter configuration object we are associated with.  If
  // this value is null, this filter instance is not currently
  // configured. 
  private FilterConfig filterConfig = null;

  public BookFormFilter() {
  }

  private void doBeforeProcessing(ServletRequest request, ServletResponse response)
      throws IOException, ServletException {
    if (debug) {
      log("LibroFormaFiltro:DoBeforeProcessing");
    }

    HttpServletRequest httpRequest = (HttpServletRequest) request;

    BookForm bookForma = null;
    if (ServletFileUpload.isMultipartContent(httpRequest)) {
      try {
        ServletFileUpload upload = new ServletFileUpload();
        FileItemIterator iterator = upload.getItemIterator(httpRequest);
        Map<String, Object> mapLibro = new HashMap<String, Object>();
        Map<String, Object> mapLibroForma = new HashMap<String, Object>();
        while (iterator.hasNext()) {
          FileItemStream item = iterator.next();
          if (item.isFormField()) {
            mapLibro.put(item.getFieldName(), Streams.asString(item.openStream()));
          } else {
            mapLibroForma.put(item.getFieldName(), item);
          }
        }
        // Manejo especial de la fecha.
        String fechaPublicacion = mapLibro.get("fecha_publicacion").toString();
        if (fechaPublicacion != null) {
          try {
            mapLibro.put("fechaPublicacion", DateUtils.parseDate(fechaPublicacion, new String[]{"MM/dd/yyyy"}));
          } catch (ParseException ex) {
            Logger.getLogger(BookFormFilter.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        bookForma = new BookForm();
        mapLibroForma.put("accion", mapLibro.remove("accion"));
        BeanUtils.populate(bookForma.getBook(), mapLibro);
        BeanUtils.populate(bookForma, mapLibroForma);
        Object idSerie = mapLibro.get("id_serie");
        if (idSerie != null) {
          bookForma.getBook().getBookSeries().setId(Integer.valueOf(idSerie.toString()));
        }
      } catch (IllegalAccessException ex) {
        Logger.getLogger(BookFormFilter.class.getName()).log(Level.SEVERE, null, ex);
      } catch (InvocationTargetException ex) {
        Logger.getLogger(BookFormFilter.class.getName()).log(Level.SEVERE, null, ex);
      } catch (FileUploadException ex) {
        Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      bookForma = ControllerUtils.getForm(BookForm.class, httpRequest);
    }
    request.setAttribute(BookLoadController.FORM_NAME, bookForma);
  }

  private void doAfterProcessing(ServletRequest request, ServletResponse response)
      throws IOException, ServletException {
    if (debug) {
      log("LibroFormaFiltro:DoAfterProcessing");
    }

    // Write code here to process the request and/or response after
    // the rest of the filter chain is invoked.

    // For example, a logging filter might log the attributes on the
    // request object after the request has been processed. 
    /*
   for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
   String name = (String)en.nextElement();
   Object value = request.getAttribute(name);
   log("attribute: " + name + "=" + value.toString());

   }
    */

    // For example, a filter might append something to the response.
    /*
   PrintWriter respOut = new PrintWriter(response.getWriter());
   respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
    */
  }

  /**
   * @param request  The servlet request we are processing
   * @param response The servlet response we are creating
   * @param chain    The filter chain we are processing
   * @throws IOException      if an input/output error occurs
   * @throws ServletException if a servlet error occurs
   */
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain)
      throws IOException, ServletException {

    if (debug) {
      log("LibroFormaFiltro:doFilter()");
    }

    doBeforeProcessing(request, response);

    Throwable problem = null;
    try {
      chain.doFilter(request, response);
    } catch (Throwable t) {
      // If an exception is thrown somewhere down the filter chain,
      // we still want to execute our after processing, and then
      // rethrow the problem after that.
      problem = t;
      t.printStackTrace();
    }

    doAfterProcessing(request, response);

    // If there was a problem, we want to rethrow it if it is
    // a known type, otherwise log it.
    if (problem != null) {
      if (problem instanceof ServletException) {
        throw (ServletException) problem;
      }
      if (problem instanceof IOException) {
        throw (IOException) problem;
      }
      sendProcessingError(problem, response);
    }
  }

  /**
   * Return the filter configuration object for this filter.
   */
  public FilterConfig getFilterConfig() {
    return (this.filterConfig);
  }

  /**
   * Set the filter configuration object for this filter.
   *
   * @param filterConfig The filter configuration object
   */
  public void setFilterConfig(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  /**
   * Destroy method for this filter
   */
  public void destroy() {
  }

  /**
   * Init method for this filter
   */
  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
    if (filterConfig != null) {
      if (debug) {
        log("LibroFormaFiltro:Initializing filter");
      }
    }
  }

  /**
   * Return a String representation of this object.
   */
  @Override
  public String toString() {
    if (filterConfig == null) {
      return ("LibroFormaFiltro()");
    }
    StringBuffer sb = new StringBuffer("LibroFormaFiltro(");
    sb.append(filterConfig);
    sb.append(")");
    return (sb.toString());
  }

  private void sendProcessingError(Throwable t, ServletResponse response) {
    String stackTrace = getStackTrace(t);

    if (stackTrace != null && !stackTrace.equals("")) {
      try {
        response.setContentType("text/html");
        PrintStream ps = new PrintStream(response.getOutputStream());
        PrintWriter pw = new PrintWriter(ps);
        pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

        // PENDING! Localize this for next official release
        pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
        pw.print(stackTrace);
        pw.print("</pre></body>\n</html>"); //NOI18N
        pw.close();
        ps.close();
        response.getOutputStream().close();
      } catch (Exception ex) {
      }
    } else {
      try {
        PrintStream ps = new PrintStream(response.getOutputStream());
        t.printStackTrace(ps);
        ps.close();
        response.getOutputStream().close();
      } catch (Exception ex) {
      }
    }
  }

  public static String getStackTrace(Throwable t) {
    String stackTrace = null;
    try {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      pw.close();
      sw.close();
      stackTrace = sw.getBuffer().toString();
    } catch (Exception ex) {
    }
    return stackTrace;
  }

  public void log(String msg) {
    filterConfig.getServletContext().log(msg);
  }
}
